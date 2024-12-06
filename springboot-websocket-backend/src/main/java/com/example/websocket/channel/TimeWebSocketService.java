package com.example.websocket.channel;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/time-service")
public class TimeWebSocketService {
	
	// 訂閱時間服務的客戶Session集合 (那些 Session 有訂閱此服務)
	private static final Set<Session> subscribers = Collections.synchronizedSet(new HashSet<>());
	
	
	@OnOpen
	public void onOpen(Session session) {
		// 發送歡迎消息
		sendMessage(session, "Welcome! 可用指令: SUBSCRIBE_TIME, UNSUBSCRIBE_TIME");
	}
	
	@OnMessage
	public void onMessage(Session session, String message) {
		switch(message) {
			case "SUBSCRIBE_TIME":
				subscribers.add(session); // 加入訂閱
				sendMessage(session, "[已訂閱]時間服務成功");
				break;
			case "UNSUBSCRIBE_TIME":
				subscribers.remove(session); // 取消訂閱
				sendMessage(session, "[已取消]訂閱時間服務成功");
				break;
			default:
				sendMessage(session, "指令錯誤! 可用指令: SUBSCRIBE_TIME, UNSUBSCRIBE_TIME");
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		// 客戶端斷開連線時要取消該客戶的訂閱
		subscribers.remove(session);
		sendMessage(session, "[已取消]訂閱時間服務成功, 因客戶端斷開連線");
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		// 出現錯誤時取消訂閱
		subscribers.remove(session);
		// 印出錯誤訊息
		throwable.printStackTrace();
	}
	
	// 發送訊息
	private void sendMessage(Session session, String message) {
		try {
			if(session.isOpen()) {
				session.getBasicRemote().sendText(message); // 同步送出
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
