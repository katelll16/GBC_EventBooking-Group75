package com.group75.BookingService.dto;

public record BookingRequest(
        Long id,
        String bookingName,
        String eventId,
        String userId,
        UserDetails userDetails) {}
