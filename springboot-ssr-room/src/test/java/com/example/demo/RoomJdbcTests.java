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
	
	@Test
	public void testRoomAdd() {
		Room room = new Room(101, "101(S)", 3);
		int rowcount = roomRepositoryJdbc.save(room);
		System.out.println("測試新增: " + room + " 結果回傳: " + rowcount + " (1 表示正確新增一筆)");
	}
	
}
