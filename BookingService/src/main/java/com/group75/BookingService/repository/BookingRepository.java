package com.group75.BookingService.repository;

import com.group75.BookingService.entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByRoomIdAndStartTimeBetween(String roomId, LocalDateTime startTime, LocalDateTime endTime); // Find bookings for a room in a time range
    List<Booking> findByUserId(String userId);
    List<Booking> findOverlappingBookings(@Param("roomId") String roomId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

}
