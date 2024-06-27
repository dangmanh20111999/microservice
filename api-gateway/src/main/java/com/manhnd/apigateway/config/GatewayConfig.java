package com.manhnd.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	
	@Value("${book.service.url}")
	private String bookServiceUrl;
	
	@Value("${employee.service.url}")
	private String employeeServiceUrl;
	
	@Value("${user.service.url}")
	private String userServiceUrl;

    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		  uri là địa chỉ đích đến service
//		  predicates: định nghĩa điều kiện để áp dụng route này
//		  filters: định nghĩa bộ lọc
        return builder.routes()
                .route("book-service", r -> r.path("/api/v1/books/**")
                        .filters(f -> f.filter(new AuthFilter()))
                        .uri(bookServiceUrl))
                .route("employee-service", r -> r.path("/api/v1/employees/**")
                        .filters(f -> f.filter(new AuthFilter()))
                        .uri(employeeServiceUrl))
                .route("user-service", r -> r.path("/api/v1/users/**")
                        .uri(userServiceUrl))
                .build();
    }
}
