package com.group75.BookingService.service;

import com.group75.BookingService.service.BookingService;
import com.group75.BookingService.entity.Booking;
import com.group75.BookingService.repository.BookingRepository;
import com.group75.BookingService.event.BookingPlacedEvent;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService { // Implementing the interface

    private final BookingRepository bookingRepository;
    private final RestTemplate restTemplate;

    private static final String ROOM_SERVICE_URL = "http://room-service/api/rooms/availability"; // Adjust URL accordingly

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, RestTemplate restTemplate) {
        this.bookingRepository = bookingRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    @CircuitBreaker(name = "roomservice", fallbackMethod = "roomAvailabilityFallback")
    public boolean isRoomAvailable(String roomId, String startTime, String endTime) {
        String url = ROOM_SERVICE_URL + "?roomId=" + roomId + "&startTime=" + startTime + "&endTime=" + endTime;
        return restTemplate.getForObject(url, Boolean.class);
    }

    public boolean roomAvailabilityFallback(String roomId, String startTime, String endTime, Throwable t) {
        return false;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(String id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public Booking createBooking(Booking booking) {
        validateBooking(booking);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(String id, Booking bookingDetails) {
        validateBooking(bookingDetails);
        bookingDetails.setId(id);
        return bookingRepository.save(bookingDetails);
    }

    @Override
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
        List<Booking> overlappingBookings = bookingRepository.findByRoomIdAndStartTimeBeforeAndEndTimeAfter(roomId, endTime, startTime);
        return !overlappingBookings.isEmpty();
    }

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }
}