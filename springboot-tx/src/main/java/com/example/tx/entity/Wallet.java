package com.example.tx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Wallet {
	
	@Id
	private String username; // 用戶
	private Integer balance; // 餘額
}
