package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.RoomMapper;
import com.example.demo.model.dto.RoomDto;
import com.example.demo.repository.RoomRepositoryJdbc;
import com.example.demo.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {
	
	@Autowired
	private RoomRepositoryJdbc roomRepositoryJdbc;
	
	@Autowired
	private RoomMapper roomMapper;
	
	@Override
	public List<RoomDto> getAllRooms() {
		return roomRepositoryJdbc.findAll()
				.stream()
				.map(roomMapper::toDto) // .map(room -> roomMapper.toDto(room))
				.collect(Collectors.toList());
	}

	@Override
	public RoomDto getRoomById(Integer roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRoom(RoomDto roomDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRoom(Integer roomId, String roomName, Integer roomSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRoom(Integer roomId, RoomDto roomDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRoom(Integer roomId, String roomName, Integer roomSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRoom(Integer roomId) {
		// TODO Auto-generated method stub
		
	}

}
