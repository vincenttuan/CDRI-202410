package javaweb.service;

import java.util.ArrayList;
import java.util.List;

import javaweb.model.dto.UserDto;
import javaweb.model.entity.User;
import javaweb.repository.UserDao;
import javaweb.repository.UserDaoImpl;

public class UserService {
	
	private UserDao userDao = new UserDaoImpl();
	
	// 所有使用者
	public List<UserDto> findAll() {
		List<UserDto> userDtos = new ArrayList<>();
		// 將 User 轉 UserDto
		// 向 userDao 索取 List<User> 集合
		List<User> users = userDao.findAllUsers();
		for(User user : users) {
			// 一個一個將 User 轉成 UserDto 並放在 userDtos 集合中
			UserDto userDto = new UserDto();
			userDto.setUserId(user.getUserId());
			userDto.setUsername(user.getUsername());
			userDto.setEmail(user.getEmail());
			userDto.setActive(user.getActive());
			userDto.setRole(user.getRole());
			
			userDtos.add(userDto);
		}
		
		return userDtos;
	}
	
}
