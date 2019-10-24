package com.example.demo;

import org.springframework.boot.web.reactive.result.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
class WebConfig implements WebFluxConfigurer {

	private final MustacheViewResolver resolver;

	// The resolver is provided by MustacheAutoConfiguration class
	WebConfig(MustacheViewResolver resolver) {
		this.resolver = resolver;
	}

	// order matters; cache will find first and render.
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.viewResolver(resolver);
	}

}
