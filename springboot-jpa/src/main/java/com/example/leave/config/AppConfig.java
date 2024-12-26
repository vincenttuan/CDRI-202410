package com.example.leave.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Springboot 啟動完成之前會先執行此程式 
public class AppConfig {
	
	@Bean
	ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		// 可選: 配置嚴格匹配模式（避免多餘或未匹配的欄位映射錯誤）
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

		return modelMapper;
	}
}
