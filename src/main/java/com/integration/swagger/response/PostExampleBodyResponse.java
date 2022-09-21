package com.integration.swagger.response;

import com.integration.swagger.dto.PostExampleBodyDto;

public class PostExampleBodyResponse {

	private String id;
	private String email;
	private String password;
	
	public PostExampleBodyResponse(PostExampleBodyDto postExampleBodyDto) {
		this.id = postExampleBodyDto.getId();
		this.email = postExampleBodyDto.getEmail();
		this.password = postExampleBodyDto.getPassword();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
