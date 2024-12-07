package com.example.apigateway;
package com.example.apigateway.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi aggregateApi() {
        return GroupedOpenApi.builder()
                .group("gateway")  // Custom name for API Gateway documentation
                .pathsToMatch("/**")  // Match all paths for the API Gateway
                .build();
    }
}
