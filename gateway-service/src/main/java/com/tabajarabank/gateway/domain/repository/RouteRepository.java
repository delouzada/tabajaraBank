package com.tabajarabank.gateway.domain.repository;

import com.tabajarabank.gateway.domain.model.RouteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<RouteInfo, Long> {

    List<RouteInfo> findByActiveTrue();

    RouteInfo findByRouteId(String routeId);

    @Transactional
    @Modifying
    @Query("UPDATE RouteInfo r SET r.active = false WHERE r.id = :id")
    void deactivateById(@Param("id") Long id);
}
