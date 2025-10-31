package com.tabajarabank.gateway.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO usado para representar o status de funcionamento do Gateway.
 * Retornado pelo endpoint /health e exibido no Swagger.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthResponse {

    private String status;   // Mensagem de status do serviço
    private String version;  // Versão do gateway
    private String service;  // Nome do serviço
    private String database; // Status do banco (ex: "PostgreSQL conectado")
}