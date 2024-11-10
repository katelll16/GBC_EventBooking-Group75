package com.group75.EventService.repository;

import com.group75.EventService.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findByEventType(String eventType); // e.g., find events by type (Workshop, Lecture, etc.)
    List<Event> findByOrganizerId(String organizerId); // Find events by organizer
    List<Event> findByRoomIdAndEventDate(String roomId, LocalDateTime eventDate); // Find events in a specific room on a given date
}
