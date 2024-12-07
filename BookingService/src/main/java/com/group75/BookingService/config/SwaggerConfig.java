package com.group75.BookingService.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("booking")
                .packagesToScan("com.group75.BookingService.controller")
                .pathsToMatch("/**")  
                .build();
    }
}
