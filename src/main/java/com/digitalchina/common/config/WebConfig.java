package com.digitalchina.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.digitalchina.common.interceptor.UPMSInterceptor;

/**
 * web通用配置
 * 可以新增拦截器、视图解析器等等。
 * @author zhang
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

	@Value("${upms.url:}")
	private String upmsUrl;
	
	@Value("${upms.interceptor.patterns:/**/*.do}")
	private String upmsInterceptorPatterns;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 * upms拦截器
		 */
		String[] patterns=upmsInterceptorPatterns.split(",");
		
		registry.addInterceptor(new UPMSInterceptor(upmsUrl)).addPathPatterns(patterns);
		super.addInterceptors(registry);
	}

}
