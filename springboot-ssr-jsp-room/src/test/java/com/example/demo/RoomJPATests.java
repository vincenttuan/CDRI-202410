package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.RoomRepositoryJdbc;

@SpringBootTest
public class RoomJPATests {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Test void testRoomAdd() {
		Room room = new Room(101, "101(S)", 3);
		room = roomRepository.save(room);
		System.out.println("測試新增: " + room);
	}
	
	@Test void testFindAllRooms() {
		System.out.println("測試查詢全部: " + roomRepository.findAll());
	}
	
	@Test void testGetOneRoom() {
		System.out.println("測試查詢單筆: " + roomRepository.findById(101));
		System.out.println("測試查詢單筆: " + roomRepository.findById(201));
	}
	
	@Test void updateRoom() {
		Room uptRoom = new Room(101, "101(L)", 100);
		System.out.println("修改前: " + uptRoom);
		Room room = roomRepository.save(uptRoom);
		System.out.println("修改後: " + room);
	}
	
	@Test void deleteRoom() {
		int roomId = 101;
		roomRepository.deleteById(roomId);
		System.out.println("測試刪除: " + roomId);
	}
	
}
