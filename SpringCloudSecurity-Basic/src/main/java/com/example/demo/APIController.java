package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableResourceServer
@RestController
public class APIController extends ResourceServerConfigurerAdapter {
	
	@GetMapping("/mypath")
	public String getMessage() {
		return "this message";
	}
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
        http
        	.requestMatcher(new RequestHeaderRequestMatcher("Authorization"))
        	.authorizeRequests().antMatchers("/testing", "/logging").permitAll()
        	.anyRequest().fullyAuthenticated();
    }
	
}
