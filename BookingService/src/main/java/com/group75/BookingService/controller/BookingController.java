package com.group75.BookingService.controller;

import com.group75.BookingService.entity.Booking;
import com.group75.BookingService.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingServiceController {
    private final BookingService bookingService;

    @Autowired
    public BookingServiceController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable String id) {
        Booking booking = bookingService.getBookingById(id);
        return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable String id, @RequestBody Booking bookingDetails) {
        return ResponseEntity.ok(bookingService.updateBooking(id, bookingDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
