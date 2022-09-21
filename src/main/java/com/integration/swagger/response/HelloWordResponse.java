package com.integration.swagger.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @AllArgsConstructor @NoArgsConstructor  @Getter @Setter 
public class HelloWordResponse {
	private String message;
	private String code;
}
