package com.gateway.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator gatewayroute(RouteLocatorBuilder builder) {
		return builder.routes()
				.route( r -> r.path("/user/**")
						.filters(f -> f.rewritePath("/user/(?<remains>.*)", "/${remains}"))
						.uri("lb://UTILISATEUR/")
//						.id("service1")
				)
				.route( r -> r.path("/interet/**")
								.filters(f -> f.rewritePath("/interet/(?<remains>.*)", "/${remains}"))
								.uri("lb://CENTREINTERET/")
//						.id("service1")
				)
				.route( r -> r.path("/publication/**")
								.filters(f -> f.rewritePath("/publication/(?<remains>.*)", "/${remains}"))
								.uri("lb://PUBLICATION/")
//						.id("service1")
				)
				.build();
	}
}
