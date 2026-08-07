//package com.kh.plugin.configuration;
//
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//
//@Configuration
//public class AiConfig {
//
//	@Bean
//	public ChatClient chatClient(ChatClient.Builder builder) {
//		return builder
//				.defaultSystem("너는 요약 전문가야 표준어를 사용해서 답변해.")
//				.build();
//	}
//	
//}
