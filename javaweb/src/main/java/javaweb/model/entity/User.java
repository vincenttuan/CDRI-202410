package javaweb.model.entity;

import lombok.Data;

// 代表 users 資料表的紀錄
/**
 * -- 建立 users 資料表
  	create table if not exists users (
  		user_id int primary key auto_increment comment '使用者ID',
   		username varchar(50) not null unique comment '使用者名稱',	
   		password_hash varchar(255) not null comment '使用者Hash密碼',
   		salt varchar(255) not null comment '隨機鹽',
   		email varchar(255) comment '電子郵件',
   		active boolean default false comment '帳號啟動',
   		role varchar(50) not null default 'ROLE_USER' comment '角色權限'
 	);
 */
@Data
public class User {
	private Integer userId; // 使用者ID
	private String username; // 使用者名稱
	private String passwordHash; // 使用者Hash密碼
	private String salt; // 隨機鹽
	private String email; // 電子郵件
	private Boolean active; // 帳號啟動
	private String role; // 角色權限
		
}
