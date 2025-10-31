package com.tabajarabank.gateway.infrastructure.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do Swagger/OpenAPI 3 para o Gateway do TabajaraBank.
 * Disponível em: http://localhost:8080/swagger-ui.html
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI tabajaraBankGatewayOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TabajaraBank Gateway API")
                        .description("Documentação da API Gateway responsável pelo roteamento entre microserviços do TabajaraBank.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipe TabajaraBank")
                                .email("suporte@tabajarabank.com.br")
                                .url("https://tabajarabank.com.br"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório do Projeto")
                        .url("https://github.com/tabajarabank/gateway-service"));
    }
}
