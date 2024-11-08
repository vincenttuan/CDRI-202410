package com.example.demo.repository.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Room;
import com.example.demo.repository.RoomRepositoryJdbc;

// 實現 RoomRepositoryJdbc 介面
//@Repository("roomJdbc")
@Repository  // 預設別名 roomRepositoryJdbcImpl (類名,字首小寫)
public class RoomRepositoryJdbcImpl implements RoomRepositoryJdbc {
	
	private static final Logger logger = LoggerFactory.getLogger(RoomRepositoryJdbcImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String findAllSql;
	private String findByIdSql;
	private String saveSql;
	private String updateSql;
	private String deleteByIdSql;
	
	@Override
	public List<Room> findAll() {
		return jdbcTemplate.query(findAllSql, new BeanPropertyRowMapper<>(Room.class));
	}

	@Override
	public Optional<Room> findById(Integer roomId) {
		// 因為 queryForObject 若沒有找到資料會自動拋出例外, 所以要 try-catch 保護
		try {
			Room room = jdbcTemplate.queryForObject(findByIdSql, new BeanPropertyRowMapper<>(Room.class), roomId);
			return Optional.of(room);
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return Optional.empty();
	}

	@Override
	public int save(Room room) {
		return jdbcTemplate.update(saveSql, room.getRoomId(), room.getRoomName(), room.getRoomSize());
	}

	@Override
	public int update(Room room) {
		return jdbcTemplate.update(updateSql, room.getRoomName(), room.getRoomSize(), room.getRoomId());
	}

	@Override
	public int deleteById(Integer roomId) {
		return jdbcTemplate.update(deleteByIdSql ,roomId);
	}
	
}
