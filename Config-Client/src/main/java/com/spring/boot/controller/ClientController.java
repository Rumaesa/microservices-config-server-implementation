package com.spring.boot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RefreshScope
public class ClientController {
	
	@Value("${db.user}")
	private String user;
	
	@Value("${db.password}")
	private String password;
	
	@GetMapping("/display")
	public String displayData() {
		return "DB Data from Git -> user :: "+user+" password :: "+password;
				
	}

}
