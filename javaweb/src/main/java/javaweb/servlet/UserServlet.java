package javaweb.servlet;

import jakarta.servlet.annotation.WebServlet;

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
 查詢單筆: GET  /user/delete?userId=1 
 
 * */

@WebServlet("/user/*")
public class UserServlet {
	
}
