package javaweb.repository;

import java.util.List;

import javaweb.model.entity.User;

// User (DAO: Data Access Object)
public interface UserDao {
	// 多筆: 查詢所有使用者
	List<User> findAllUsers();
	
	// 單筆: 根據 username 查詢該筆使用者
	User getUser(String username);
	
	// 新增
	void addUser(User user);
	
	// 修改 active 狀態
	void updateUserActive(Integer userId, Boolean active);
	
	// 修改 role 狀態
	void updateUserRole(Integer userId, String role);
		
	// 刪除: 根據 userId 來刪除
	void deleteUser(Integer userId);
	
	// 修改密碼
	void updatePasswordHash(Integer userId, String newPasswordHash);
}
