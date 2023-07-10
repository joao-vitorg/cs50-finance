package com.example.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Documentação da API CS50 Finance")
                .version("Version 1 (v1)")
                .description("Esta documentação apresenta os endpoints responsáveis pelas funcionalidades de Stock, User e Transaction."));
    }
}
