package com.group75.RoomService.service;

import com.group75.RoomService.entity.Room;
import com.group75.RoomService.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    }
}
