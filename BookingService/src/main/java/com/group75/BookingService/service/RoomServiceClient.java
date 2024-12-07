package com.group75.BookingService.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class RoomServiceClient {

    private final RestTemplate restTemplate;

    public RoomServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "roomservice", fallbackMethod = "roomServiceFallback")
    public String checkRoomAvailability(Long roomId) {
        String url = "http://roomservice/api/rooms/" + roomId + "/availability";
        return restTemplate.getForObject(url, String.class);
    }

    public String roomServiceFallback(Long roomId, Throwable t) {
        return "Room availability could not be verified at the moment. Please try again later.";
    }
}
