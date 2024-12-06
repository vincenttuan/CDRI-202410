package com.example.websocket.channel;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

/**
 * WebSocketService 使用 WebSocket 技術提供兩項服務：
 * 1. 時間推送服務 (TimService)：每秒向訂閱者推送當前時間。
 * 2. 隨機數推送服務 (LottoService)：每5秒向訂閱者推送一個隨機數。
 * 客戶端可以通過指令訂閱或取消訂閱這些服務。
 */
@ServerEndpoint(value = "/service")
public class WebSocketService {

    // 管理所有訂閱時間服務的客戶端 Session
    private static final Set<Session> timeSubscribers = Collections.synchronizedSet(new HashSet<>());

    // 管理所有訂閱隨機數服務的客戶端 Session
    private static final Set<Session> lottoSubscribers = Collections.synchronizedSet(new HashSet<>());

    // 每個客戶端 Session 都有一個專屬的消息隊列
    private static final Map<Session, BlockingQueue<String>> messageQueues = new ConcurrentHashMap<>();

    // 定期執行推送任務的線程池
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    // 隨機數生成器
    private static final Random random = new Random();

    // 定時推送服務的靜態初始化塊
    static {
        // 定期推送時間服務消息，每秒執行一次
        scheduler.scheduleAtFixedRate(() -> {
            String timeMessage = "Time: " + LocalDateTime.now(); // 當前時間消息
            synchronized (timeSubscribers) {
                for (Session session : timeSubscribers) {
                    enqueueMessage(session, timeMessage); // 將消息加入客戶端的消息隊列
                }
            }
        }, 0, 1, TimeUnit.SECONDS);

        // 定期推送隨機數服務消息，每5秒執行一次
        scheduler.scheduleAtFixedRate(() -> {
            String lottoMessage = "Lotto Number: " + random.nextInt(100); // 隨機數消息
            synchronized (lottoSubscribers) {
                for (Session session : lottoSubscribers) {
                    enqueueMessage(session, lottoMessage); // 將消息加入客戶端的消息隊列
                }
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    /**
     * 當客戶端連接到 WebSocket 時觸發。
     * 初始化客戶端的消息隊列並啟動獨立的消息發送執行緒。
     */
    @OnOpen
    public void onOpen(Session session) {
        // 為新連接的客戶端創建消息隊列
        messageQueues.put(session, new LinkedBlockingQueue<>());

        // 啟動獨立的消息發送執行緒
        startMessageSender(session);

        // 發送歡迎消息
        try {
            session.getBasicRemote().sendText("Welcome! 可用指令: SUBSCRIBE_TIME, SUBSCRIBE_LOTTO, UNSUBSCRIBE_TIME, UNSUBSCRIBE_LOTTO");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 當客戶端發送消息時觸發。
     * 根據客戶端的指令進行相應操作（如訂閱或取消訂閱服務）。
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        switch (message.toUpperCase()) {
            case "SUBSCRIBE_TIME":
                timeSubscribers.add(session); // 新增到時間服務訂閱者列表
                enqueueMessage(session, "已訂閱時間服務.");
                break;
            case "UNSUBSCRIBE_TIME":
                timeSubscribers.remove(session); // 從時間服務訂閱者列表移除
                enqueueMessage(session, "已取消訂閱時間服務.");
                break;
            case "SUBSCRIBE_LOTTO":
                lottoSubscribers.add(session); // 新增到隨機數服務訂閱者列表
                enqueueMessage(session, "已訂閱隨機數服務.");
                break;
            case "UNSUBSCRIBE_LOTTO":
                lottoSubscribers.remove(session); // 從隨機數服務訂閱者列表移除
                enqueueMessage(session, "已取消訂閱隨機數服務.");
                break;
            default:
                enqueueMessage(session, "未知指令。可用指令: SUBSCRIBE_TIME, SUBSCRIBE_LOTTO, UNSUBSCRIBE_TIME, UNSUBSCRIBE_LOTTO");
        }
    }

    /**
     * 當客戶端斷開連接時觸發。
     * 清理與該客戶端相關的資源。
     */
    @OnClose
    public void onClose(Session session) {
        timeSubscribers.remove(session); // 從時間服務訂閱者列表移除
        lottoSubscribers.remove(session); // 從隨機數服務訂閱者列表移除
        messageQueues.remove(session); // 移除消息隊列
    }

    /**
     * 當 WebSocket 發生錯誤時觸發。
     * 清理與該客戶端相關的資源並記錄錯誤。
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        timeSubscribers.remove(session);
        lottoSubscribers.remove(session);
        messageQueues.remove(session);
        throwable.printStackTrace();
    }

    /**
     * 將消息加入指定客戶端的消息隊列。
     * @param session 客戶端 Session
     * @param message 要發送的消息
     */
    private static void enqueueMessage(Session session, String message) {
        BlockingQueue<String> queue = messageQueues.get(session);
        if (queue != null) {
            queue.offer(message); // 將消息加入隊列
        }
    }

    /**
     * 為指定的客戶端啟動一個獨立的消息發送執行緒。
     * @param session 客戶端 Session
     */
    private void startMessageSender(Session session) {
        new Thread(() -> {
            BlockingQueue<String> queue = messageQueues.get(session);
            while (session.isOpen()) {
                try {
                    String message = queue.take(); // 從隊列中取出消息
                    synchronized (session) {
                        session.getBasicRemote().sendText(message); // 同步發送消息
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // 處理中斷
                    break;
                } catch (IOException e) {
                    e.printStackTrace(); // 記錄發送失敗
                }
            }
        }).start();
    }
}
