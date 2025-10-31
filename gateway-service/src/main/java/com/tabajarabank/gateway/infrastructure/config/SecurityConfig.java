package com.tabajarabank.gateway.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Configuração global de segurança para o Gateway.
 * 
 * - Remove qualquer tipo de autenticação automática (HTTP Basic, Form Login)
 * - Desativa CSRF para permitir requisições POST, PUT e DELETE sem token
 * - Libera o acesso a todas as rotas (útil em ambientes de desenvolvimento e teste)
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    /**
     * Define a cadeia de filtros de segurança do Spring WebFlux.
     * 
     * @param http Instância de ServerHttpSecurity usada para configurar as regras de segurança.
     * @return A cadeia de filtros de segurança configurada.
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
                // -------------------------------------------------------------------------
                // 1️. DESATIVA O CSRF (Cross-Site Request Forgery)
                // -------------------------------------------------------------------------
                // O CSRF serve para evitar que formulários externos enviem requisições maliciosas.
                // Como este projeto é uma API (sem sessão web tradicional), podemos desativar.
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                // -------------------------------------------------------------------------
                // 2️. DESATIVA O LOGIN BÁSICO (aquele pop-up chato de usuário/senha do navegador)
                // -------------------------------------------------------------------------
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)

                // -------------------------------------------------------------------------
                // 3️⃣ DESATIVA O FORMULÁRIO DE LOGIN HTML PADRÃO DO SPRING
                // -------------------------------------------------------------------------
                // Isso impede o Spring de renderizar uma página HTML de login automaticamente.
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)

                // -------------------------------------------------------------------------
                // 4️. LIBERA O ACESSO A TODAS AS ROTAS
                // -------------------------------------------------------------------------
                // Em produção, você pode querer restringir isso e proteger rotas específicas.
                .authorizeExchange(exchange -> exchange
                        .anyExchange().permitAll()
                )

                // Finaliza e aplica a configuração
                .build();
    }
}
