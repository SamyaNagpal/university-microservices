package com.university.college.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

  @Bean
  public RouteLocator routes(RouteLocatorBuilder builder) {
    return builder.routes()

        .route("courseservice", r -> r.path("/courses/**")
            .uri("lb://COURSESERVICE"))

        .route("enrollmentservice", r -> r.path("/enrollments/**")
            .uri("lb://ENROLLMENTSERVICE"))

        .route("studentservice", r -> r.path("/students/**")
            .uri("lb://STUDENTSERVICE"))

        .route("authservice", r -> r.path("/auth/**")
                .uri("lb://AUTHSERVICE"))

        .build();
  }
}
