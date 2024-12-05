package com.example.websocket.channel;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.messaging.Message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

/**
 * @ServerEndpoint 表示一個 WebSocket 端點(Endpoint)
 * WebSocket 連接路徑: ws://localhost:8080/channel/chat
 * */

@ServerEndpoint(value = "/channel/chat")
public class ChatChannel {
	
	// 利用 Set + synshronized 來保存所有 session
	private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
	
	// 廣播消息給所有連接用戶
	private void broadcast(String sessionId, String message) {
		sessions.forEach(session -> {
					if(session.isOpen()) {
						session.getAsyncRemote().sendText("[ " + sessionId + " 說]: " + message);
					}
				});
	}
	
	// 發送消息給指定用戶
	private void sendToSession(String targetId, String sessionId, String message) {
        sessions.stream()
                .filter(session -> session.getId().equals(targetId) && session.isOpen())
                .findFirst()
                .ifPresent(session -> session.getAsyncRemote().sendText("[ " + sessionId + " 私訊]: " + message));
    }
	
	// Session 指的是 WebSocket 的連線 
	// 每一個連線都有獨立的 session 與 id (自動分配)
	@OnOpen // 當客戶端與伺服器建立連接時觸發。
	public void onOpen(Session session) {
		// 將新建立的 session 加入到集合
		sessions.add(session);
		
		System.out.println("[ "+ session.getId() + " 已連線]");
		// 回應 (只有自己知道)
		//session.getAsyncRemote().sendText("[ "+ session.getId() + " 已連線]");
		// 廣播消息
		broadcast(session.getId(), "已連線");
	}
	
	@OnMessage // 當伺服器收到來自客戶端的消息時觸發。
	public void onMessage(String jsonMessage, Session session) {
		JsonObject messageObject = JsonParser.parseString(jsonMessage).getAsJsonObject();
		String type = messageObject.get("type").getAsString();
		String target = messageObject.get("target").getAsString();
		String message = messageObject.get("message").getAsString();
		
		if ("message".equals(type)) {
			if ("all".equals(target)) {
				// 廣播消息
				broadcast(session.getId(), message);
			} else {
				// 指定
				sendToSession(target, session.getId(), message);
			}
		}
	}
	
	@OnClose // 當客戶端與伺服器的連接被關閉時觸發。
	public void onClose(Session session, CloseReason closeReason) {
		sessions.remove(session); // 移除 session
		System.out.println("[ "+ session.getId() + " 已離線], 原因: " + closeReason);
		// 廣播消息
		broadcast(session.getId(), "離線了");
	}
	
	@OnError // 當 WebSocket 通訊過程中發生錯誤時觸發。
	public void onError(Session session, Throwable throwable) {
		sessions.remove(session); // 移除 session
		System.out.println("[ "+ session.getId() + " 連線異常], 原因: " + throwable);
	}
}
