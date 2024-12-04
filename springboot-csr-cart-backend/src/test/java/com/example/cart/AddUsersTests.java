package com.example.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cart.model.entity.User;
import com.example.cart.repository.UserRepository;

@SpringBootTest
class AddUsersTests {
	@Autowired
	UserRepository userRepository;
	
	@Test
	void add() {
		User user1 = new User();
		user1.setUsername("admin");
		user1.setPassword("1234");
		userRepository.save(user1);
		
		User user2 = new User();
		user2.setUsername("mary");
		user2.setPassword("1234");
		userRepository.save(user2);
		
	}

}
