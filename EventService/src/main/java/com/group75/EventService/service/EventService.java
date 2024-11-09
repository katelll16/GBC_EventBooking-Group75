package com.group75.EventService.service;

import com.group75.EventService.entity.Event;
import com.group75.EventService.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(String id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event) {
        validateEvent(event);
        return eventRepository.save(event);
    }

    public Event updateEvent(String id, Event eventDetails) {
        validateEvent(eventDetails);
        eventDetails.setId(id);
        return eventRepository.save(eventDetails);
    }

    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
    }

    private void validateEvent(Event event) {
        if (event.getEventName() == null || event.getEventName().isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be empty.");
        }
        if (event.getExpectedAttendees() < 1) {
            throw new IllegalArgumentException("Expected attendees must be greater than zero.");
        }
    }
}
