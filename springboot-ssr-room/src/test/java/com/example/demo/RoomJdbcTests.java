package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.repository.RoomRepositoryJdbc;

@SpringBootTest
public class RoomJdbcTests {
	
	@Autowired
	//@Qualifier("roomJdbc")
	private RoomRepositoryJdbc roomRepositoryJdbc;
	
	@Test
	public void testRoomAdd() {
		
	}
	
}
