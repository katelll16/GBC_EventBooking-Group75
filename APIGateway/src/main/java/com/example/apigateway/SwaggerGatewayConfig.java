package com.group75.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerGatewayConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2) // Specify Swagger 2
                .select()
                .apis(RequestHandlerSelectors.any()) // Scan all APIs
                .paths(PathSelectors.any()) // Include all paths
                .build();
    }
}
