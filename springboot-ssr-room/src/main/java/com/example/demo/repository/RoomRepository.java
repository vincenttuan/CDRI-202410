package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> { // Room: Entity, Integer: @Id 的型別
	// 預設對自動實現 CRUD
}
