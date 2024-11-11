package com.example.demo.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepositoryJdbc;

@Repository
@PropertySource("classpath:sql.properties") 
public class UserRepositoryJdbcImpl implements UserRepositoryJdbc {
	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryJdbcImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${user.sql.getUser}") // ${} SpringEL 語法
	private String getUserSql;
	
	@Override
	public User getUser(String username) {
		try {
			return jdbcTemplate.queryForObject(getUserSql, new BeanPropertyRowMapper<>(User.class), username);
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return null;
	}

}
