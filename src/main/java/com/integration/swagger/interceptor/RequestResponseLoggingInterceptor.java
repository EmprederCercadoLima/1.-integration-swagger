package com.integration.swagger.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RequestResponseLoggingInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info(".:. Start process Url [{}:{}] - Request-query[{}]::Request-body[{}]", request.getMethod(), request.getRequestURL(), request.getQueryString(), request.getParameterMap());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		if(response != null && response.getContentType() != null ) {
			log.info(".:. End process Status {} - Response {}", response.getStatus(), response.getContentType().getBytes().toString());	
		}
	}
	
}
