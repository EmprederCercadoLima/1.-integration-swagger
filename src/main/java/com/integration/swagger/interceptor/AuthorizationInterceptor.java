package com.integration.swagger.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.swagger.util.LoadResponseInterceptorErrorUtil;
import com.integration.swagger.util.ResponseInterceptorErrorUtil;

import lombok.extern.slf4j.Slf4j;

/*
 * This intercepter only works for services of type channels
 * */
@Slf4j
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
	
	private static final String REQUEST_AUTHORIZATION = "authorization";
    private static final String REQUEST_BEARER_TOKEN_COMMERCE = "Bearer";
    private static final String REQUEST_BEARER_TOKEN_NIUBIZ = "Bearer-niubiz";
    
    private static final String MESSAGE_ERROR_CREDENTIALS = "Error credentials";
    private static final String MESSAGE_INVALID_CREDENTIALS_COMMERCE = "Invalid credentials user commerce";
    private static final String MESSAGE_INVALID_CREDENTIALS_NIUBIZ = "Invalid credentials user niubiz";

	ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			if(request.getHeader(REQUEST_AUTHORIZATION) != null) {
				String authorization = request.getHeader(REQUEST_AUTHORIZATION);
				String[] parts = authorization.split(" ");
				
				if(parts.length != 2) {
					this.errorResponseInterceptor(response, HttpServletResponse.SC_FORBIDDEN, "FORBBIDEN", MESSAGE_ERROR_CREDENTIALS);
					return false;
				}
				
				Boolean isValidBearerCommerce = parts[0].equals(REQUEST_BEARER_TOKEN_COMMERCE);
				Boolean isValidBearerNiubiz = parts[0].equals(REQUEST_BEARER_TOKEN_NIUBIZ);
				if(isValidBearerCommerce) {
					return this.isValidAuthorizationUserCommerce(parts[1], response);
				} else if(isValidBearerNiubiz) {
					return this.isValidAuthorizationUserNiubiz(parts[1], response);
				}
			}
			this.errorResponseInterceptor(response, HttpServletResponse.SC_FORBIDDEN, "FORBBIDEN", MESSAGE_ERROR_CREDENTIALS);
			return false;
		} catch(Exception ex) {
			this.errorResponseInterceptor(response, HttpServletResponse.SC_FORBIDDEN, "FORBBIDEN", ex.getMessage());
			return false;
		}
		
	}
	
	private boolean isValidAuthorizationUserCommerce(String token, HttpServletResponse response) throws JsonProcessingException, IOException {
		Boolean isValidToken = token.equals("REQUEST_BEARER_TOKEN_COMMERCE");
		return this.isValidToken(true, isValidToken, response, MESSAGE_INVALID_CREDENTIALS_COMMERCE);
	}
	
	private boolean isValidAuthorizationUserNiubiz(String token, HttpServletResponse response) throws JsonProcessingException, IOException {
		Boolean isValidToken = token.equals("REQUEST_BEARER_TOKEN_NIUBIZ");
		return this.isValidToken(false, isValidToken, response, MESSAGE_INVALID_CREDENTIALS_NIUBIZ);
	}

	private boolean isValidToken (boolean isTokenCommerce, boolean isValidToken, HttpServletResponse response, String message) throws JsonProcessingException, IOException {
		if(!isValidToken) {
			this.errorResponseInterceptor(response, HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED", message);
			return false;	
		}
		return true;
	}
	
	private void errorResponseInterceptor(HttpServletResponse response, int status, String name, String message) throws JsonProcessingException, IOException {
		LoadResponseInterceptorErrorUtil getLoadResponseInterceptorErrorUtil = this.getLoadResponseInterceptorErrorUtil(String.valueOf(status), name, message);
		response.setContentType("application/json");
		response.setStatus(status);
		response.getWriter().write(this.mapper.writeValueAsString(getLoadResponseInterceptorErrorUtil));
		log.error(".:. Error process - Token AuthenticationException {}", message);
	}
	
	private LoadResponseInterceptorErrorUtil getLoadResponseInterceptorErrorUtil (String statusCode, String statusText, String statusMessage) {
		List<ResponseInterceptorErrorUtil> listResponseInterceptorErrorUtil = new ArrayList<>();
		LoadResponseInterceptorErrorUtil loadResponseInterceptorErrorUtil = new LoadResponseInterceptorErrorUtil();
		loadResponseInterceptorErrorUtil.setMetada(statusCode, statusText,statusMessage, "integration-swagger:[" + AuthorizationInterceptor.class + "]");
		loadResponseInterceptorErrorUtil.getListResponseInterceptorErrorUtil().setHelloWordResponse(listResponseInterceptorErrorUtil);
		return loadResponseInterceptorErrorUtil;
	}
	
}
