package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Springboot 在啟動完成前會先行執行此配置
public class ModelMapperConfig {
	
	@Bean  // 由 Springboot 來管理此物件(IOC)
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
