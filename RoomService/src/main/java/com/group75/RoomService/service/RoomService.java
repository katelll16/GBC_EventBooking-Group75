package com.group75.RoomService.service;

import com.group75.RoomService.entity.Room;
import com.group75.RoomService.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;


import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public Room createRoom(Room room) {
        validateRoom(room);
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room roomDetails) {
        validateRoom(roomDetails);
        roomDetails.setId(id);
        return roomRepository.save(roomDetails);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    public List<Room> findAvailableRooms() {
        return roomRepository.findByAvailability(true);
    }

    private void validateRoom(Room room) {
        if (room.getCapacity() <= 0) {
            throw new IllegalArgumentException("Room capacity must be greater than 0.");
        }
        if (room.getRoomName() == null || room.getRoomName().isEmpty()) {
            throw new IllegalArgumentException("Room name cannot be empty.");
        }

        @SpringBootTest
        public class RoomServiceIntegrationTest {

            static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("test_db")
                    .withUsername("user")
                    .withPassword("password");

            @BeforeAll
            static void startContainer() {
                postgres.start();
            }

            @DynamicPropertySource
            static void databaseProperties(DynamicPropertyRegistry registry) {
                registry.add("spring.datasource.url", postgres::getJdbcUrl);
                registry.add("spring.datasource.username", postgres::getUsername);
                registry.add("spring.datasource.password", postgres::getPassword);
            }
    }
}
