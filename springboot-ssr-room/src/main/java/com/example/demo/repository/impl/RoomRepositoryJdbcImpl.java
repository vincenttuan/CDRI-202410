package com.example.demo.repository.impl;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.entity.Room;
import com.example.demo.repository.RoomRepositoryJdbc;

// 實現 RoomRepositoryJdbc 介面
public class RoomRepositoryJdbcImpl implements RoomRepositoryJdbc {

	@Override
	public List<Room> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Room> findById(Integer room) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public int save(Room room) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Room room) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Integer roomId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
