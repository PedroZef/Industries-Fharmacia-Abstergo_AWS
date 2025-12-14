package com.abstergo.fharmacia.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI abstergoOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Abstergo Fharmacia API")
                        .description("API da plataforma virtual da Abstergo Industries")
                        .version("v1.0")
                        .license(new License().name("MIT").url("https://opensource.org/licenses/MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório no GitHub")
                        .url("https://github.com/PedroZef/Industries-Fharmacia-Abstergo_AWS"));
    }

}
