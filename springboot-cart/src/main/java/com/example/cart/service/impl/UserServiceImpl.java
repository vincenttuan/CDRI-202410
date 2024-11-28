package com.example.cart.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.exception.ProductNotFoundException;
import com.example.cart.exception.UserNotFoundException;
import com.example.cart.model.dto.FavoriteProductDTO;
import com.example.cart.model.dto.FavoriteUserDTO;
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
	
	// 用戶關注列表(用戶關注那些商品)
	@Override
	public List<FavoriteProductDTO> getFavoriteProducts(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("用戶不存在"));
		// 該用戶關注的商品集合
		Set<Product> products = user.getFavoriteProducts();
		// 將 products 集合中每一個 Product 元素轉 FavoriteProductDTO
		return products.stream()
						.map(product -> modelMapper.map(product, FavoriteProductDTO.class)) // 元素轉換
						.toList();
	}
	
	// 商品關注列表(商品被那些用戶關注)
	@Override
	public List<FavoriteUserDTO> getFavoriteUsers(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 新增商品關注
	@Override
	public void addFavoriteProduct(Long userId, Long productId) {
		// TODO Auto-generated method stub
		
	}
	
	// 移除商品關注
	@Override
	public void removeFavoriteProduct(Long userId, Long productId) {
		// TODO Auto-generated method stub
		
	}
	
	
}
