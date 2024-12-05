package com.example.websocket.channel;

import java.io.IOException;

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
	
	// Session 指的是 WebSocket 的連線
	// 每一個連線都有獨立的 session 與 id (自動分配)
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("[ "+ session.getId() + " 已連線]");
		// 回應
		session.getAsyncRemote().sendText("[ "+ session.getId() + " 已連線]");
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		// 回應
		session.getAsyncRemote().sendText("[ " + session.getId() + " 說]: " + message);
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("[ "+ session.getId() + " 已離線], 原因: " + closeReason);
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println("[ "+ session.getId() + " 連線異常], 原因: " + throwable);
	}
}
