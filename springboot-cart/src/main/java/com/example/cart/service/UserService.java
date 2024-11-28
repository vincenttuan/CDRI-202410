package com.example.cart.service;

import java.util.List;
import java.util.Optional;

import com.example.cart.model.dto.LoginDTO;
import com.example.cart.model.dto.UserDTO;
import com.example.cart.model.entity.Product;

public interface UserService {
	Optional<UserDTO> findByUsername(String username);
	Optional<UserDTO> login(LoginDTO loginDTO);
	Optional<UserDTO> saveUser(UserDTO userDTO);
	
	
}
