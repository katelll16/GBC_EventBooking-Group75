package com.group75.BookingService.service;

import com.group75.BookingService.entity.Booking;
import com.group75.BookingService.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(String id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Booking createBooking(Booking booking) {
        validateBooking(booking);
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(String id, Booking bookingDetails) {
        validateBooking(bookingDetails);
        bookingDetails.setId(id);
        return bookingRepository.save(bookingDetails);
    }

    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }

    private void validateBooking(Booking booking) {
        if (booking.getStartTime().isAfter(booking.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
        if (hasOverlap(booking.getRoomId(), booking.getStartTime(), booking.getEndTime())) {
            throw new IllegalArgumentException("This room is already booked for the specified time.");
        }
    }

    private boolean hasOverlap(String roomId, LocalDateTime startTime, LocalDateTime endTime) {
        List<Booking> overlappingBookings = bookingRepository.findOverlappingBookings(roomId, startTime, endTime);
        return !overlappingBookings.isEmpty();
    }
}
