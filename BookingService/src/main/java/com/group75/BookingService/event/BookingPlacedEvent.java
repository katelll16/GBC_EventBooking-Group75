package com.group75.BookingService.event;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingPlacedEvent {
    private String bookingNumber;
    private String email;
}
