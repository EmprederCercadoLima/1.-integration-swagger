package com.integration.swagger.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoadResponseInterceptorErrorUtil  extends ResponseMetadataUtil {
	
	private ListResponseInterceptorErrorUtil listResponseInterceptorErrorUtil = new ListResponseInterceptorErrorUtil();
	
	public ListResponseInterceptorErrorUtil getListResponseInterceptorErrorUtil() {
		return listResponseInterceptorErrorUtil;
	}

	public void setListResponseInterceptorErrorUtil(ListResponseInterceptorErrorUtil listResponseInterceptorErrorUtil) {
		this.listResponseInterceptorErrorUtil = listResponseInterceptorErrorUtil;
	}
}
