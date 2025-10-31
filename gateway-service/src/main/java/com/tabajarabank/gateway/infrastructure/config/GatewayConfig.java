package com.tabajarabank.gateway.infrastructure.config;

import com.tabajarabank.gateway.domain.model.RouteInfo;
import com.tabajarabank.gateway.domain.service.GatewayService;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final GatewayService gatewayService;

    public GatewayConfig(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        var routes = builder.routes();

        for (RouteInfo route : gatewayService.getActiveRoutes()) {
            routes.route(route.getRouteId(),
                    r -> r.path(route.getPath())
                          .and()
                          .method(route.getMethod())
                          .uri(route.getDestination())); // ← alterado aqui
        }

        return routes.build();
    }
}
