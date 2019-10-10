package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
public class SpringCloudSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSecurityApplication.class, args);
	}
	
	
}
