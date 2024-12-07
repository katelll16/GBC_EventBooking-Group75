package com.group75.ApprovalService.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class UserServiceClient {

    private final RestTemplate restTemplate;

    public UserServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "userservice", fallbackMethod = "userRoleFallback")
    public String fetchUserRole(Long userId) {
        String url = "http://userservice/api/users/" + userId + "/role";
        return restTemplate.getForObject(url, String.class);
    }

    public String userRoleFallback(Long userId, Throwable t) {
        return "Default role: guest";
    }
}
