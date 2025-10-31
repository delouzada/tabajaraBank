package com.tabajarabank.gateway.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gateway_routes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "route_id", nullable = false, unique = true)
    private String routeId;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private String method;

    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;
}
