package com.example.websocket.channel;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/time-service")
public class TimeWebSocketService {
	
	@OnOpen
	public void onOpen(Session session) {
		
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		
	}
	
	@OnClose
	public void onClose(Session session, Throwable throwable) {
		
	}
	
	@OnError
	public void onOpen(Session session, String message) {
		
	}
	
}
