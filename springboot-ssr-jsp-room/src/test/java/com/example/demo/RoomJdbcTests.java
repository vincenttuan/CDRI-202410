package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Room;
import com.example.demo.repository.RoomRepositoryJdbc;

@SpringBootTest
public class RoomJdbcTests {
	
	@Autowired
	//@Qualifier("roomJdbc")
	private RoomRepositoryJdbc roomRepositoryJdbc;
	
	@Test void testRoomAdd() {
		Room room = new Room(301, "301(S)", 3);
		int rowcount = roomRepositoryJdbc.save(room);
		System.out.println("測試新增: " + room + " 結果回傳: " + rowcount + " (1 表示正確新增一筆)");
	}
	
	@Test void testFindAllRooms() {
		System.out.println("測試查詢全部: " + roomRepositoryJdbc.findAll());
	}
	
	@Test void testGetOneRoom() {
		System.out.println("測試查詢單筆: " + roomRepositoryJdbc.findById(101));
		System.out.println("測試查詢單筆: " + roomRepositoryJdbc.findById(109));
	}
	
	@Test void updateRoom() {
		Room uptRoom = new Room(101, "101(L)", 100);
		int rowcount = roomRepositoryJdbc.update(uptRoom);
		System.out.println("測試修改: " + uptRoom + " 結果回傳: " + rowcount + " (1 表示正確修改一筆)");
	}
	
	@Test void deleteRoom() {
		int roomId = 101;
		int rowcount = roomRepositoryJdbc.deleteById(roomId);
		System.out.println("測試刪除: " + roomId + " 結果回傳: " + rowcount + " (1 表示刪除修改一筆)");
	}
	
}
