package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.mapper.RoomMapper;

@SpringBootTest
class SpringbootSsrRoomApplicationTests {
	
	@Autowired
	private RoomMapper roomMapper;
	
	@Test
	void testRoomMapper() {
		
	}

}
