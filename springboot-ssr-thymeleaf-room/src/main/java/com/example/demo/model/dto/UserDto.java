package com.example.demo.model.dto;

import lombok.Data;

// UserDto 對應於 User entity
// Dto 的屬性名稱建議可以與所對應的 Entity 物件不同
@Data
public class UserDto {
	private Integer userId; // 使用者ID
	private String username; // 使用者名稱
	private String email; // 電子郵件
	private Boolean active; // 帳號啟動
	private String role; // 角色權限
}
