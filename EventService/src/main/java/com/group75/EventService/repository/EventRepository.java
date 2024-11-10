package com.group75.EventService.repository;

import com.group75.EventService.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findByEventType(String eventType);
    List<Event> findByOrganizerId(String organizerId);
    List<Event> findByRoomIdAndEventDate(String roomId, LocalDateTime eventDate);
}
