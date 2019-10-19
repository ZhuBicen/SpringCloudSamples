package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

import java.net.URI;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		return http
				.authorizeExchange()
				.pathMatchers("/login",
						"/bye",
						"/favicon.ico",
						"/images/**")
				.permitAll()
				.pathMatchers("/**")
				.authenticated()
				.and()
				.csrf()
				//.csrfTokenRepository(customCsrfTokenRepository)
				//.requireCsrfProtectionMatcher(customCsrfMatcher)
				.and()
				.formLogin()
				.loginPage("/login")
				.authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/"))
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessHandler(logoutSuccessHandler("/bye"))
				.and()
				.build();
	}

	public ServerLogoutSuccessHandler logoutSuccessHandler(String uri) {
		RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
		successHandler.setLogoutSuccessUrl(URI.create(uri));
		return successHandler;
	}
}
