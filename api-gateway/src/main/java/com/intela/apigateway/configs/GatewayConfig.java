package com.intela.apigateway.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableHystrix
public class GatewayConfig {
    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                //Todo: include predict api
                .route("profile-service", r -> r.path("/api/v1/profile/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8085")) //lb://profile-service

                .route("favourite-service", r -> r.path("/api/v1/favorite/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://favorite-service"))

                .route("crypto-service", r -> r.path("/api/v1/profile/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://profile-service"))

                .route("auth-service", r -> r.path("/api/v1/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8081"))
                //Todo: make it use uri below
                //lb://auth-service

                .build();
    }
}
