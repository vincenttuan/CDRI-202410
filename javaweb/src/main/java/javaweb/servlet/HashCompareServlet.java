package javaweb.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 GET /hash/compare 得到 hash_compare.jsp
 +----------+---------+
 | username |         |  <- input
 +----------+---------+
 | password |         |  <- input
 +----------+---------+
 |  Reset   |  Submit |  <- button 
 +----------+---------+
 
 按下 Submit button
 POST /hash/compare 
 進行資料比對
  
 * */
@WebServlet("/hash/compare")
public class HashCompareServlet extends HttpServlet {
	private static Map<String, String> users = new ConcurrentHashMap<>();
	static {
		users.put("john", "");
		users.put("mary", "");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
