package com.journalapp.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openapi() {
        return new OpenAPI()
            .info(new Info()
                .title("journal App Apis")
                .description("by aditya"))
            .servers(List.of(
                new Server().url("http://localhost:8080").description("local"),
                new Server().url("http://localhost:8081").description("prod")
            ));
    }
}
