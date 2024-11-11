package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.RoomAlreadyExistsException;
import com.example.demo.exception.RoomException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.model.dto.RoomDto;
import com.example.demo.model.entity.Room;
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
		return roomRepositoryJdbc.findAll()  // List<Room>
				.stream()
				.map(roomMapper::toDto) // .map(room -> roomMapper.toDto(room))
				.collect(toList());
	}

	@Override
	public RoomDto getRoomById(Integer roomId) {
		Room room = roomRepositoryJdbc.findById(roomId)
				.orElseThrow(() -> new RoomNotFoundException("找不到會議室: roomId: " + roomId));
		
		return roomMapper.toDto(room);
	}

	@Override
	public void addRoom(RoomDto roomDto) {
		// 判斷該 room 是否已經存在 ?
		Optional<Room> optRoom = roomRepositoryJdbc.findById(roomDto.getRoomId());
		if(optRoom.isPresent()) { // 房間已存在
			throw new RoomAlreadyExistsException("新增失敗: " + roomDto.getRoomId() + " 已存在");
		}
		
		Room room = roomMapper.toEntity(roomDto);
		int rowcount = roomRepositoryJdbc.save(room);
		if(rowcount == 0) {
			throw new RoomException("無法新增");
		}
	}

	@Override
	public void addRoom(Integer roomId, String roomName, Integer roomSize) {
		RoomDto roomDto = new RoomDto(roomId, roomName, roomSize);
		addRoom(roomDto);
	}

	@Override
	public void updateRoom(Integer roomId, RoomDto roomDto) {
		// 判斷該 room 是否已經存在 ?
		Optional<Room> optRoom = roomRepositoryJdbc.findById(roomId);
		if(optRoom.isEmpty()) { // 房間不存在
			throw new RoomNotFoundException("修改失敗: " + roomId + " 不存在");
		}
		
		roomDto.setRoomId(roomId);
		Room room = roomMapper.toEntity(roomDto);
		int rowcount = roomRepositoryJdbc.update(room);
		if(rowcount == 0) {
			throw new RoomException("無任何紀錄有被修改");
		}
	}

	@Override
	public void updateRoom(Integer roomId, String roomName, Integer roomSize) {
		RoomDto roomDto = new RoomDto(roomId, roomName, roomSize);
		updateRoom(roomId, roomDto);
	}

	@Override
	public void deleteRoom(Integer roomId) {
		// 判斷該 room 是否已經存在 ?
		Optional<Room> optRoom = roomRepositoryJdbc.findById(roomId);
		if(optRoom.isEmpty()) { // 房間不存在
			throw new RoomNotFoundException("刪除失敗: " + roomId + " 不存在");
		}
		int rowcount = roomRepositoryJdbc.deleteById(roomId);
		if(rowcount == 0) {
			throw new RoomException("無任何紀錄有被刪除");
		}
	}

}
