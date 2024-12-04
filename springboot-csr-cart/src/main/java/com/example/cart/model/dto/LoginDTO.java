package com.example.cart.model.dto;

import lombok.Data;

@Data
public class LoginDTO {
	private String username;
	private String password;
	private Boolean isLoggedIn; // 是否登入成功 ?
}
