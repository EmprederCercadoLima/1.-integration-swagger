package com.integration.swagger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/integration-swagger/v2")
public class IntegrationSwaggerController2 {

	@GetMapping("/hello-word")
	public String getHelloWord() {
		return "HellowordController2";
	}
	
}
