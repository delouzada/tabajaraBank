package com.tabajarabank.gateway.infrastructure.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Filtro global responsável por logar todas as requisições que passam pelo Gateway.
 * Útil para auditoria, rastreamento e depuração.
 */
@Slf4j
@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String method = request.getMethod() != null ? request.getMethod().name() : "UNKNOWN";
        String path = request.getURI().getPath();
        String clientIp = request.getRemoteAddress() != null ?
                request.getRemoteAddress().getAddress().getHostAddress() : "desconhecido";

        log.info("➡️  [REQ] {} {} | IP: {}", method, path, clientIp);

        long startTime = System.currentTimeMillis();

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    long duration = System.currentTimeMillis() - startTime;
                    int statusCode = exchange.getResponse().getStatusCode() != null
                            ? exchange.getResponse().getStatusCode().value()
                            : 0;

                    log.info("⬅️  [RES] {} {} | Status: {} | Tempo: {} ms",
                            method, path, statusCode, duration);
                }));
    }

    /**
     * Define a prioridade do filtro.
     * Filtros com menor número são executados antes.
     */
    @Override
    public int getOrder() {
        return -1; // executa antes da maioria dos filtros padrão
    }
}
