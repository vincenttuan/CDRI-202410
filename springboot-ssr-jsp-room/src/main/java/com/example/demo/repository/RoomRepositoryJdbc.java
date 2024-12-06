package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.entity.Room;

public interface RoomRepositoryJdbc {
	List<Room> findAll();
	Optional<Room> findById(Integer roomId);
	int save(Room room); 
	int update(Room room);
	int deleteById(Integer roomId);
}
