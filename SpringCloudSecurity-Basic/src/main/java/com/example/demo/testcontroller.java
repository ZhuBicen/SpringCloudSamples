package com.example.demo;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableOAuth2Sso
public class testcontroller extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private OAuth2ClientContext clientContext;
	
	@GetMapping("/testing")
	public String test(Principal principal) {
		
		String token = clientContext.getAccessToken().getValue();
		
		OAuth2Authentication authentication = (OAuth2Authentication) principal;
        Map<String, Object> user = (Map<String, Object>) authentication.getUserAuthentication().getDetails();
        
		System.out.println(user);
        
        
		return "Hello Bharat +++ Your token    " + token;
	}
	
	@GetMapping("/logging")
	public String loggin() {
		return "Login Man!!!!";
	} 

	
	public void configure(HttpSecurity http) throws Exception {
		try
		{
			http.authorizeRequests()
			.antMatchers("/", "/logging").permitAll()
			.anyRequest().authenticated();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		
	}
	
}
