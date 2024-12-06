package com.example.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.example.websocket.channel.ChatChannel;
import com.example.websocket.channel.StockWebSocketService;
import com.example.websocket.channel.TimeWebSocketService;
import com.example.websocket.channel.WebSocketService;

@Configuration
public class WebSocketConfig {
	
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		ServerEndpointExporter exporter = new ServerEndpointExporter();
		// 註冊 WebSocket 端點
		// 門號: /channel/chat, /time-service
		exporter.setAnnotatedEndpointClasses(ChatChannel.class, TimeWebSocketService.class, StockWebSocketService.class, WebSocketService.class);
		
		return exporter;
	}
	
}
