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
 
 查詢全部: GET  /user, /users
 新增單筆: POST /user/add
 查詢單筆: GET  /user/get?username=admin
 修改單筆: POST /user/update?userId=1
 刪除單筆: GET  /user/delete?userId=1 
 
 * */

@WebServlet(urlPatterns = {"/user/*", "/users"})
public class UserServlet extends HttpServlet {
	
	private UserService userService = new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		//resp.getWriter().print(pathInfo);
		if(pathInfo == null || pathInfo.equals("/*")) { // 查詢全部
			// 查詢全部
			List<UserDto> userDtos = userService.findAll();
			//resp.getWriter().print(userDtos);
			// 將必要資料加入到 request 屬性中以便交由 jsp 進行分析與呈現
			req.setAttribute("userDtos", userDtos);
			// 內重導到 user.jsp
			req.getRequestDispatcher("/WEB-INF/view/user.jsp").forward(req, resp);
			return;
		} else if(pathInfo.equals("/delete")) { // 刪除
			String userId = req.getParameter("userId");
			userService.deleteUser(userId);
			// 刪除完畢之後, 重新執行指定頁面
			resp.sendRedirect("/javaweb/user");
			return;
		} else if(pathInfo.equals("/get")) { // 取得 user 資料並導入到修改頁面
			String username = req.getParameter("username");
			UserDto userDto = userService.getUser(username);
			// 將必要資料加入到 request 屬性中以便交由 jsp 進行分析與呈現
			req.setAttribute("userDto", userDto);
			// 內重導到 user_update.jsp
			req.getRequestDispatcher("/WEB-INF/view/user_update.jsp").forward(req, resp);
			return;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		// 取得表單資料
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String role = req.getParameter("role");
		String active = req.getParameter("active"); // update 專用
		String userId = req.getParameter("userId"); // update 專用
		
		switch (pathInfo) {
			case "/add":
				userService.appendUser(username, password, email, role);
				break;
			case "/update":
				userService.updateUser(userId, active, role);
				break;
		}
		
		// 外重導到指定 URL 網頁
		//resp.sendRedirect("http://tw.yahoo.com");
		//resp.sendRedirect("http://localhost:8080/javaweb/user");
		resp.sendRedirect("/javaweb/user");
		
	}
	
	
	
}
