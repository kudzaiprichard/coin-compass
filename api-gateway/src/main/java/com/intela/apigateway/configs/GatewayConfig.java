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
                .route("profile-service", r -> r.path("/api/v1/profile/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://profile-service")) //

                .route("favourite-service", r -> r.path("/api/v1/favorite/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://favorite-service"))

                .route("crypto-service", r -> r.path("/api/v1/profile/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://crypto-service"))

                .route("predict-service", r -> r.path("/api/v1/predict/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://predict-service"))

                .route("auth-service", r -> r.path("/api/v1/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth-service"))

                .build();
    }
}
