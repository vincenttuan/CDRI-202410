package javaweb.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javaweb.model.dto.UserDto;
import javaweb.service.UserService;

/**
 
 MVC + 自訂框架
  
  request   +-------------+          +-------------+          +---------+
 ---------> | UserServlet | -------> | UserService | -------> | UserDao | ------->    MySQL
            | (Controller)| <------- |             | <------- |         | <------- (web.users)
  			+-------------+  UserDto +-------------+   User   +---------+
  			       |          (Dto)                  (Entity)
  			       |
  			       v
  			+-------------+
 <--------- |   user.jsp  |
  response	|    (View)   |
  			+-------------+                 
 
 查詢全部: GET  /user
 查詢單筆: GET  /user/get?username=admin
 新增單筆: POST /user/add
 修改單筆: POST /user/update?userId=1
 刪除單筆: GET  /user/delete?userId=1 
 
 * */

@WebServlet(urlPatterns = {"/user/*", "/users"})
public class UserServlet extends HttpServlet {
	
	private UserService userService = new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		resp.getWriter().print(pathInfo);
		if(pathInfo == null) {
			// 查詢全部
			List<UserDto> userDtos = userService.findAll();
			resp.getWriter().print(userDtos);
			return;
		}
		
	}
	
}
