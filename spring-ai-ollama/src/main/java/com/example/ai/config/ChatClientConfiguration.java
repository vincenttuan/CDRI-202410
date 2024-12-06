package com.example.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfiguration {
    // Configuration for ChatClient
	
	@Bean
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder.build();
	}
	
}