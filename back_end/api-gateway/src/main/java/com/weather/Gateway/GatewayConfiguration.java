package com.weather.Gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

		return builder.routes()
				.route(p->p.path("/location/**")
						.filters(f -> f.addResponseHeader("Origin", "http://localhost:4200"))
						.uri("lb://location-service"))
				.build();
	}
}
