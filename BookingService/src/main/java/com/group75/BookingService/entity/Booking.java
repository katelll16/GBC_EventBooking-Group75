package com.group75.BookingService.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;

    @NotEmpty(message = "User ID cannot be empty")
    private String userId;

    @NotEmpty(message = "Room ID cannot be empty")
    private String roomId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String purpose;

}
