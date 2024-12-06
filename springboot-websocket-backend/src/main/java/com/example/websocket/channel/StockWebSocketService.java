package com.example.websocket.channel;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.websocket.util.YahooStockScraper;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/stock-service")
public class StockWebSocketService {
	
	// 訂閱報價服務的客戶Session集合 (那些 Session 有訂閱此服務)
	private static final Set<Session> subscribers = Collections.synchronizedSet(new HashSet<>());
	
	// 定期任務執行器
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	static {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		// 任務
		Runnable task = () -> {
			// 取得報價資料
			// 範例: {最低=1065, 昨收=1075, 漲跌=5.00, 最高=1080, 成交=1070, 均價=1071, 成交金額(億)=178.06, 漲跌幅=0.47, 振幅=1.40, 開盤=1075, 昨量=31354, 總量=16617}
			String symbol = "2330.TW"; // ^TWII = 加權股價指數, 2330.TW = 台積電, 1101.TW = 台泥 ...
			Map<String, String> map = YahooStockScraper.getPrice(symbol);
			String stockMessage = "台積電(" + symbol + "): <p />今日成交" + map.get("成交") + " <p />成交金額(億): " + map.get("成交金額(億)") + " <p />漲跌: " + map.get("漲跌") + " <p />漲跌幅: " + map.get("漲跌幅") + " % <p />時間: " + sdf.format(new Date());
			// 發送通知給有訂閱的人
			for(Session session : subscribers) {
				sendMessage(session, stockMessage);
			}
		};
		// 定期執行
		scheduler.scheduleAtFixedRate(task, 0, 3, TimeUnit.SECONDS);
		
	}
	
	@OnOpen
	public void onOpen(Session session) {
		// 發送歡迎消息
		sendMessage(session, "Welcome! 可用指令: SUBSCRIBE_STOCK, UNSUBSCRIBE_STOCK");
	}
	
	@OnMessage
	public void onMessage(Session session, String message) {
		switch(message) {
			case "SUBSCRIBE_STOCK":
				subscribers.add(session); // 加入訂閱
				sendMessage(session, "[已訂閱]時間股票報價服務成功");
				break;
			case "UNSUBSCRIBE_STOCK":
				subscribers.remove(session); // 取消訂閱
				sendMessage(session, "[已取消]訂閱股票報價服務成功");
				break;
			default:
				sendMessage(session, "指令錯誤! 可用指令: SUBSCRIBE_STOCK, UNSUBSCRIBE_STOCK");
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		// 客戶端斷開連線時要取消該客戶的訂閱
		subscribers.remove(session);
		sendMessage(session, "[已取消]訂閱股票報價服務成功, 因客戶端斷開連線");
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		// 出現錯誤時取消訂閱
		subscribers.remove(session);
		// 印出錯誤訊息
		throwable.printStackTrace();
	}
	
	// 發送訊息
	private static void sendMessage(Session session, String message) {
		try {
			if(session.isOpen()) {
				session.getBasicRemote().sendText(message); // 同步送出
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
