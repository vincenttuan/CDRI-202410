package com.example.demo.repository.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.controller.ApiController;
import com.example.demo.model.entity.Room;
import com.example.demo.repository.RoomRepositoryJdbc;

// 實現 RoomRepositoryJdbc 介面
@Repository
public class RoomRepositoryJdbcImpl implements RoomRepositoryJdbc {
	
	private static final Logger logger = LoggerFactory.getLogger(RoomRepositoryJdbcImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Room> findAll() {
		String sql = "select room_id, room_name, room_size from room";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Room.class));
	}

	@Override
	public Optional<Room> findById(Integer roomId) {
		String sql = "select room_id, room_name, room_size from room where room_id = ?";
		// 因為 queryForObject 若沒有找到資料會自動拋出例外, 所以要 try-catch 保護
		try {
			Room room = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Room.class), roomId);
			return Optional.of(room);
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return Optional.empty();
	}

	@Override
	public int save(Room room) {
		String sql = "insert into room(room_id, room_name, room_size) values(?, ?, ?)";
		return jdbcTemplate.update(sql, room.getRoomId(), room.getRoomName(), room.getRoomSize());
	}

	@Override
	public int update(Room room) {
		String sql = "update room set room_name = ?, room_size = ? where room_id = ?";
		return jdbcTemplate.update(sql, room.getRoomName(), room.getRoomSize(), room.getRoomId());
	}

	@Override
	public int deleteById(Integer roomId) {
		String sql = "delete from room where room_id = ?";
		return jdbcTemplate.update(sql ,roomId);
	}
	
}
