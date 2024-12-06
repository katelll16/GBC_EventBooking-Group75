package com.group75.BookingService.repository;

import com.group75.BookingService.entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {

    List<Booking> findByRoomIdAndStartTimeBeforeAndEndTimeAfter(String roomId, LocalDateTime endTime, LocalDateTime startTime);

    List<Booking> findByUserId(String userId);

    List<Booking> findByRoomIdAndStartTimeBetween(String roomId, LocalDateTime startTime, LocalDateTime endTime);
}
