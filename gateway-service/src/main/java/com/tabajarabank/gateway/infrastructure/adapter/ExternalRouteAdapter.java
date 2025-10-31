package com.tabajarabank.gateway.infrastructure.adapter;

import com.tabajarabank.gateway.domain.model.RouteInfo;
import com.tabajarabank.gateway.domain.repository.RouteRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalRouteAdapter {

    private final RouteRepository routeRepository;

    public ExternalRouteAdapter(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<RouteInfo> findAllActiveRoutes() {
        return routeRepository.findByActiveTrue();
    }

    public RouteInfo findByRouteId(String routeId) {
        return routeRepository.findByRouteId(routeId);
    }

    public RouteInfo save(RouteInfo routeInfo) {
        return routeRepository.save(routeInfo);
    }

    public void deactivate(Long id) {
        routeRepository.deactivateById(id);
    }

    public void delete(Long id) {
        routeRepository.deleteById(id);
    }
}
