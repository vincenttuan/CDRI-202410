package com.example.demo.repository;

import com.example.demo.model.entity.User;

public interface UserRepositoryJdbc {
	User getUser(String username);
}
