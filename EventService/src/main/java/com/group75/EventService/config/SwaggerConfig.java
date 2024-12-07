package com.group75.EventService.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("event")
                .packagesToScan("com.group75.EventService.controller")
                .pathsToMatch("/**")
                .build();
    }
}
