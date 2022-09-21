package com.integration.swagger.response;

import com.integration.swagger.util.ResponseMetadataUtil;

public class LoadHelloWordResponse extends ResponseMetadataUtil {

	private ListHelloWordResponse listHelloWordResponse = new ListHelloWordResponse();
	
	public ListHelloWordResponse getListHelloWordResponse() {
		return listHelloWordResponse;
	}

	public void setListHelloWordResponse(ListHelloWordResponse listHelloWordResponse) {
		this.listHelloWordResponse = listHelloWordResponse;
	}
	
}
