package javaweb.repository;

import java.sql.Connection;

public class BaseDao {
	protected static Connection conn;
	
	static {
		String username = "root";
		String password = "abc123";
		String dbUrl = "jdbc:mysql://localhost:3306web?serverTimezone=Asia/Taipei&characterEncoding=utf-8&useUnicode=true";
	}
}
