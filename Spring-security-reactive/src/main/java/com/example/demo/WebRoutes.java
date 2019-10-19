package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class WebRoutes {

	@Bean
	RouterFunction<?> iconResources() {
		return RouterFunctions
				.resources("/favicon.**", new ClassPathResource("images/favicon.ico"));
	}

	@Bean
	RouterFunction<?> viewRoutes() {
		return RouterFunctions
				.route(RequestPredicates.GET("/login"),
						req -> {
							System.out.println("long filter, not printed");
							return ServerResponse
										.ok()
										.render("login-form",
												req.exchange().getAttributes());
				}
				)
				.andRoute(RequestPredicates.GET("/bye"),
						req -> ServerResponse.ok().render("bye")
				)
				.filter((req, resHandler) ->
						req.exchange()
								.getAttributeOrDefault(
										CsrfToken.class.getName(),
										Mono.empty().ofType(CsrfToken.class)
								)
								.flatMap(csrfToken -> {
									req.exchange()
											.getAttributes()
											.put(csrfToken.getParameterName(), csrfToken);
									return resHandler.handle(req);
								}))
				.andRoute(RequestPredicates.GET("/"),
						req -> req.principal()
								.ofType(Authentication.class)
								.flatMap(auth -> {
									User user = User.class.cast(auth.getPrincipal());
									req.exchange()
											.getAttributes()
											.putAll(Collections.singletonMap("user", user));
									return ServerResponse.ok().render("game",
											req.exchange().getAttributes());
								})
				);



	}

}
