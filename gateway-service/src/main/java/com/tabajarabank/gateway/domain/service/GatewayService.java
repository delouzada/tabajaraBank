package com.tabajarabank.gateway.domain.service;

import com.tabajarabank.gateway.domain.model.RouteInfo;
import com.tabajarabank.gateway.infrastructure.adapter.ExternalRouteAdapter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GatewayService {

    private final ExternalRouteAdapter externalRouteAdapter;

    public GatewayService(ExternalRouteAdapter externalRouteAdapter) {
        this.externalRouteAdapter = externalRouteAdapter;
    }

    public List<RouteInfo> getActiveRoutes() {
        return externalRouteAdapter.findAllActiveRoutes();
    }

    public Optional<RouteInfo> getRouteById(String routeId) {
        RouteInfo route = externalRouteAdapter.findByRouteId(routeId);
        return Optional.ofNullable(route);
    }

    public RouteInfo saveRoute(RouteInfo routeInfo) {
        return externalRouteAdapter.save(routeInfo);
    }

    public void deactivateRoute(Long id) {
        externalRouteAdapter.deactivate(id);
    }

    public void deleteRoute(Long id) {
        externalRouteAdapter.delete(id);
    }
}
