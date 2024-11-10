package com.group75.RoomService.repository;

import com.group75.RoomService.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByAvailability(boolean availability); // Find available or unavailable rooms
    List<Room> findByCapacityGreaterThanEqual(int capacity); // Find rooms with a minimum capacity
}