package javaweb.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javaweb.model.entity.User;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public List<User> findAllUsers() {
		List<User> users = new ArrayList<>();
		String sql = "select user_id, username, password_hash, salt, email, active, role from users";
		try(Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
			// 逐筆尋訪
			while (rs.next()) {
				// 建立 user 物件並將資料配置進去
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPasswordHash(rs.getString("password_hash"));
				user.setSalt(rs.getString("salt"));
				user.setEmail(rs.getString("email"));
				user.setActive(rs.getBoolean("active"));
				user.setRole(rs.getString("role"));
				// 將 user 物件放到 users 集合中保存
				users.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users; // 回傳有 user 物件的集合
	}

	@Override
	public User getUser(String username) {
		String sql = "select user_id, username, password_hash, salt, email, active, role from users where username=?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, username); // 第一個 ? 放 username
			
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) { // 若有得到一筆
					// 建立 user 物件並將資料配置進去
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					user.setUsername(rs.getString("username"));
					user.setPasswordHash(rs.getString("password_hash"));
					user.setSalt(rs.getString("salt"));
					user.setEmail(rs.getString("email"));
					user.setActive(rs.getBoolean("active"));
					user.setRole(rs.getString("role"));
					return user; // 返回 user 物件
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserActive(Boolean active) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserRole(String role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
	}

}
