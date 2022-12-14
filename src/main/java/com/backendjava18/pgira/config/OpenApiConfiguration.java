package com.backendjava18.pgira.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI getOpenApi(){
        //http://localhost:8088/swagger-ui/index.html
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth"
                        , new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                    .title("Gira Application")
                    .description("Service for Learning Purpose")
                    .version("v1.0")
                    .license(new License().name("NO LICENSE").url("https://superkhanhdz.000webhostapp.com"))
                    .contact(new Contact()
                            .email("khanhpro1234567890@gmail.com")
                            .name("Do Minh Khanh")
                            .url("https://github.com/CoderKhanhDZ/gira")))
                .externalDocs(new ExternalDocumentation()
                .description("Spring Doucmentation")
                .url("https://docs.spring.io/spring-framework/docs/current/reference/html/"));
    }
}
