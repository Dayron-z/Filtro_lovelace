package com.example.filtro.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Gestión de contenido ",
                description = "Contenido",
                version = "1.0.0",
                contact = @Contact(
                        name = "Contenido",
                        url = "Contenido.com",
                        email = "Contenido@gmail.com"
                )
        )
)
public class SwaggerConfig {
}
