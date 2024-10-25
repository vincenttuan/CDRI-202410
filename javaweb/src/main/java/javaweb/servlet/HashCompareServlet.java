package javaweb.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

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
	
}
