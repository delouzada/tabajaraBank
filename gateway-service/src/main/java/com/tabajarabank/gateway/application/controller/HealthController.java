package com.tabajarabank.gateway.application.controller;

import com.tabajarabank.gateway.application.dto.HealthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Controller responsável por expor o endpoint de status do Gateway.
 * Checa o status do serviço e também a conexão com o banco PostgreSQL.
 */
@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class HealthController {

    private final DataSource dataSource;

    @GetMapping
    public ResponseEntity<HealthResponse> checkHealth() {
        String dbStatus = checkDatabaseConnection();

        HealthResponse response = HealthResponse.builder()
                .status("Gateway ativo e operante")
                .version("v1.0")
                .service("TabajaraBank Gateway Service")
                .database(dbStatus)
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * Testa se o PostgreSQL está acessível.
     */
    private String checkDatabaseConnection() {
        try (Connection conn = dataSource.getConnection()) {
            if (conn.isValid(2)) {
                return "PostgreSQL conectado";
            } else {
                return "Falha na conexão com o banco";
            }
        } catch (Exception e) {
            return "Erro ao conectar ao banco: " + e.getMessage();
        }
    }
}
