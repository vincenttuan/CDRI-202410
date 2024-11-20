package com.example.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cart.model.entity.User;
import com.example.cart.repository.UserRepository;

@SpringBootTest
class AddUserTests {
	@Autowired
	UserRepository userRepository;
	
	@Test
	void addUser() {
		User user = new User
	}

}
