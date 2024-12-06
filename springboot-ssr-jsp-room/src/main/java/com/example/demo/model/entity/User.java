package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // 實體類與資料表對應(會自動建立資料表)
@Table(name = "users") // 若資料表名與實體類一致可以不用設定此行
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId; // 使用者ID
	
	@Column(name = "username")
	private String username; // 使用者名稱
	
	@Column(name = "password_hash")
	private String passwordHash; // 使用者Hash密碼
	
	@Column(name = "salt")
	private String salt; // 隨機鹽
	
	@Column(name = "email")
	private String email; // 電子郵件
	
	@Column(name = "active")
	private Boolean active; // 帳號啟動
	
	@Column(name = "role")
	private String role; // 角色權限
		
}
