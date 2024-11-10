package com.group75.BookingService.service;

import com.group75.BookingService.entity.Booking;
import com.group75.BookingService.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.MongoDBContainer;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;

        @Autowired
        private BookingRepository bookingRepository;

        @Autowired
        private RestTemplate restTemplate;

        private static final String ROOM_SERVICE_URL = "http://room-service/api/rooms/availability"; // Adjust URL accordingly

        public boolean isRoomAvailable(String roomId, String startTime, String endTime) {
            // Call RoomService to check if the room is available
            String url = ROOM_SERVICE_URL + "?roomId=" + roomId + "&startTime=" + startTime + "&endTime=" + endTime;
            return restTemplate.getForObject(url, Boolean.class);
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
    public void save(Booking booking) {
            bookingRepository.save(booking);
        }
        @SpringBootTest
        public class BookingServiceIntegrationTest {

            static MongoDBContainer mongoDB = new MongoDBContainer("mongo:latest");

            @BeforeAll
            static void startContainer() {
                mongoDB.start();
            }

            @DynamicPropertySource
            static void databaseProperties(DynamicPropertyRegistry registry) {
                registry.add("spring.data.mongodb.uri", mongoDB::getReplicaSetUrl);
            }
}
