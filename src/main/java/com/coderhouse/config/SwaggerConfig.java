package com.coderhouse.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
	
	@Bean
	OpenAPI custonOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("API REST Full | Java | Small Ecommers")
						.version("1.0.0")
						.description("La API REST proporciona endpoints para un pequeño Ecommers para manejo "
                        		+ "de clientes, productos y ventas, con su detalle de ventas "
                        		+ "CRUD (Crear, Leer, Actualizar, Eliminar) tanto para Clientes como "
                        		+ "para Productos. Los endpoints permiten listar, agregar, mostrar, "
                        		+ "editar y eliminar Clientes y productos. La API está documentada utilizando "
                        		+ "Swagger")
						.contact(new Contact()
								.name("Gabriel Carissimo")
								.email("gcarissimo@gmail.com")
								.url(""))
						.license(new License()
								.name("Licencia")
								.url("https://github.com/Drako01/java-62940?tab=MIT-1-ov-file"))
						)
						.servers(List.of(new Server()
								.url("http://localhost:8080")
								.description("Servidor Local")));
	}
}
