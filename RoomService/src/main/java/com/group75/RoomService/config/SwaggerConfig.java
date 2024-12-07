package com.group75.RoomService.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("room")
                .packagesToScan("com.group75.RoomService.controller")
                .pathsToMatch("/**")
                .build();
    }
}
