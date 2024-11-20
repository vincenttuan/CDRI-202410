package com.example.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cart.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
