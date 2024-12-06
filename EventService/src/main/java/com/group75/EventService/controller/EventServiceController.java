package com.group75.EventService.controller;

import com.group75.EventService.entity.Event;
import com.group75.EventService.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventServiceController {
    private final EventService eventService;

    // Constructor-based dependency injection (Recommended approach)
    @Autowired
    public EventServiceController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping("/create")
    public String createEvent(@RequestBody Event event) {
        if (eventService.isOrganizerAuthorized(event.getOrganizerId())) {
            eventService.save(event);
            return "Event created successfully";
        }
        return "Organizer does not have permission to create the event";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable String id, @RequestBody Event eventDetails) {
        return ResponseEntity.ok(eventService.updateEvent(id, eventDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
