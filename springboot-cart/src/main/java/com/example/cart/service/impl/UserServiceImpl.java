package com.example.cart.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.exception.ProductNotFoundException;
import com.example.cart.exception.UserNotFoundException;
import com.example.cart.model.dto.LoginDTO;
import com.example.cart.model.dto.UserDTO;
import com.example.cart.model.entity.Product;
import com.example.cart.model.entity.User;
import com.example.cart.repository.ProductRepository;
import com.example.cart.repository.UserRepository;
import com.example.cart.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Optional<UserDTO> findByUsername(String username) {
		Optional<User> optUser = userRepository.findByUsername(username);
		if(optUser.isEmpty()) Optional.empty();
		// 利用 modelMapper 將 User 轉 UserDTO
		UserDTO userDTO = modelMapper.map(optUser.get(), UserDTO.class);
		return Optional.of(userDTO);
	}

	@Override
	public Optional<UserDTO> login(LoginDTO loginDTO) {
		Optional<User> optUser = userRepository.findByUsername(loginDTO.getUsername());
		// 判斷密碼
		if(optUser.isPresent() && optUser.get().getPassword().equals(loginDTO.getPassword())) {
			return Optional.of(modelMapper.map(optUser.get(), UserDTO.class));
		} 
		return Optional.empty();
	}

	@Override
	public Optional<UserDTO> saveUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		user = userRepository.save(user);
		
		return Optional.of(modelMapper.map(user, UserDTO.class));
	}
	
	
}
