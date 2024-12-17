package com.example.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.filter.SecurityHeaderFilter;

@Configuration
public class FilterConfig {
	
	// 註冊 Filter
	@Bean
	public FilterRegistrationBean<SecurityHeaderFilter> securityHeaderFilter() {
		FilterRegistrationBean<SecurityHeaderFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new SecurityHeaderFilter());
		registrationBean.addUrlPatterns("/*"); // 針對所有路徑
		registrationBean.setName("Security Headers Filter");
		registrationBean.setOrder(1);
		return registrationBean;
	}
	
}
