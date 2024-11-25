package com.example.cart.service;

import java.util.Optional;

import com.example.cart.model.dto.LoginDTO;
import com.example.cart.model.dto.UserDTO;

public interface UserService {
	Optional<UserDTO> findByUsername(String username);
	Optional<UserDTO> login(LoginDTO loginDTO);
	Optional<UserDTO> saveUser(UserDTO userDTO);
}
