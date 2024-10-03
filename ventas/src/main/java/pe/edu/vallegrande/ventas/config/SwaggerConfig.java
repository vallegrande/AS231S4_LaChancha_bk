package pe.edu.vallegrande.ventas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .servers(Collections.singletonList(new Server().url("https://bxvdkzkm-8080.brs.devtunnels.ms/")))
                .info(new Info()
                        .title("Ventas API")
                        .description("Victor Manuel Cuaresma")
                        .version("1.0.0")
                );
    }
}
