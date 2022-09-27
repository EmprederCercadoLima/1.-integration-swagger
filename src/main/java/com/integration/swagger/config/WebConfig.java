package com.integration.swagger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.integration.swagger.interceptor.AuthorizationInterceptor;
import com.integration.swagger.interceptor.HeaderInterceptor;
import com.integration.swagger.interceptor.RequestResponseLoggingInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	RequestResponseLoggingInterceptor requestResponseLoggingInterceptor;
	
	@Autowired
	HeaderInterceptor headerInterceptor;

	@Autowired
	AuthorizationInterceptor authorizationInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestResponseLoggingInterceptor);
		//registry.addInterceptor(headerInterceptor);
		//registry.addInterceptor(authorizationInterceptor);
	}
	
}
