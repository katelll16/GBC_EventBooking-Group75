package com.group75.ApprovalService.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class EventServiceClient {

    private final RestTemplate restTemplate;

    @Autowired
    public EventServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isEventValid(String eventId) {
        // Call to the EventService API to validate the event
        String url = "http://event-service/api/events/validate/" + eventId;
        return restTemplate.getForObject(url, Boolean.class);
    }
}
