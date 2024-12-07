package com.group75.BookingService.service;

import com.group75.BookingService.entity.Booking;
import java.util.List;

public interface BookingService {
    boolean isRoomAvailable(String roomId, String startTime, String endTime);
    List<Booking> getAllBookings();
    Booking getBookingById(String id);
    Booking createBooking(Booking booking);
    Booking updateBooking(String id, Booking bookingDetails);
    void deleteBooking(String id);
}