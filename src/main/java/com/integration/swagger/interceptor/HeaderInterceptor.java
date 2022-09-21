package com.integration.swagger.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.swagger.util.LoadResponseInterceptorErrorUtil;
import com.integration.swagger.util.ResponseInterceptorErrorUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HeaderInterceptor  implements HandlerInterceptor {
	
    private static final String REQUEST_ID_KEY = "REQUEST_ID";
    private static final String REQUEST_DATE_KEY = "REQUEST_DATE";
    private static final String REQUEST_APP_CODE_KEY = "REQUEST_APP_CODE";
    
    @Value("${headers.request-id}") String REQUEST_ID_VALUE;
    @Value("${headers.request-date}") String REQUEST_DATE_VALUE;
    @Value("${headers.request-app-code}") String REQUEST_APP_CODE_VALUE;
    
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		try {

			Boolean isValidRequestId = request.getHeader(REQUEST_ID_KEY) != null ? request.getHeader(REQUEST_ID_KEY).equals(REQUEST_ID_VALUE) : false;
			Boolean isValidRequestDate = request.getHeader(REQUEST_DATE_KEY) != null ? request.getHeader(REQUEST_DATE_KEY).equals(REQUEST_DATE_VALUE) : false;
			Boolean isValidRequestAppCode = request.getHeader(REQUEST_APP_CODE_KEY) != null ? request.getHeader(REQUEST_APP_CODE_KEY).equals(REQUEST_APP_CODE_VALUE) : false;

			if(!(isValidRequestId && isValidRequestDate  && isValidRequestAppCode)) {
				LoadResponseInterceptorErrorUtil getLoadResponseInterceptorErrorUtil = this.getLoadResponseInterceptorErrorUtil(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED), "UNAUTHORIZED", "Invalid Credentials");
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write(mapper.writeValueAsString(getLoadResponseInterceptorErrorUtil));
				log.error(".:. Error process - Header AuthenticationException {}", "Invalid Credentials");
				return false;
			}
			return true;
		} catch(Exception ex) {
			LoadResponseInterceptorErrorUtil getLoadResponseInterceptorErrorUtil = this.getLoadResponseInterceptorErrorUtil(String.valueOf(HttpServletResponse.SC_FORBIDDEN), "FORBBIDEN", "Error Credentials");
			 response.setContentType("application/json");
			 response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			 response.getWriter().write(mapper.writeValueAsString(getLoadResponseInterceptorErrorUtil));
			 log.error(".:. Error process - Header AuthenticationException {}", "Error credentials");
			 return false;
		}

	}
	
	private LoadResponseInterceptorErrorUtil getLoadResponseInterceptorErrorUtil (String statusCode, String statusText, String statusMessage) {
		List<ResponseInterceptorErrorUtil> listResponseInterceptorErrorUtil = new ArrayList<>();
		LoadResponseInterceptorErrorUtil loadResponseInterceptorErrorUtil = new LoadResponseInterceptorErrorUtil();
		loadResponseInterceptorErrorUtil.setMetada(statusCode, statusText,statusMessage, "integration-swagger");
		loadResponseInterceptorErrorUtil.getListResponseInterceptorErrorUtil().setHelloWordResponse(listResponseInterceptorErrorUtil);
		return loadResponseInterceptorErrorUtil;
	}
	
}
