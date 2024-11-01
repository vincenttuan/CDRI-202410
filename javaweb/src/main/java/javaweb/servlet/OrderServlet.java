package javaweb.servlet;

/**
 * 訂單
+----------+---------+------------+--------------+----------+------------+----------+--------------+
| order_id | user_id | order_date |  product_id  | quantity | unit_price | subtotal | order_status |
+----------+---------+------------+--------------+----------+------------+----------+--------------+
| 1        | 1       | 2024-09-19 | 1            | 2        | 30000.00   | 60000.00 | Finished     |
| 2        | 1       | 2024-09-20 | 2            | 5        | 15000.00   | 75000.00 | Finished     |
| 3        | 3       | 2024-09-21 | 3            | 3        | 3000.00    | 9000.00  | Pending      |
| 4        | 4       | 2024-09-22 | 2            | 1        | 15000.00   | 15000.00 | Cancel       |
| 5        | 5       | 2024-09-23 | 5            | 4        | 8000.00    | 32000.00 | Pending      |
+----------+---------+------------+--------------+----------+------------+----------+--------------+

-- 創建訂單表
create table if not exists orders (
	order_id   int primary key auto_increment comment '訂單Id',
	user_id    int not null comment 'User Id',
	order_date varchar(50) not null comment '訂購日期',
	product_id int not null comment '商品 Id',
	quantity   int not null default 0 null comment '訂購數量',
	unit_price decimal(10, 2) not null comment '商品單價',
	subtotal   decimal(10, 2) not null comment '小計',
	order_status ENUM('Pending', 'Finished', 'Cancel') NOT NULL DEFAULT 'Pending' comment '訂單狀態'
);

insert into orders(user_id, order_date, product_id, quantity, unit_price, subtotal, order_status) 
values(1, '2024-09-19', 1, 2, 30000.00, 60000.00, 'Finished');

insert into orders(user_id, order_date, product_id, quantity, unit_price, subtotal, order_status) 
values(1, '2024-09-20', 2, 5, 15000.00, 75000.00, 'Finished');

insert into orders(user_id, order_date, product_id, quantity, unit_price, subtotal, order_status) 
values(3, '2024-09-21', 3, 3, 3000.00, 9000.00, 'Pending');

insert into orders(user_id, order_date, product_id, quantity, unit_price, subtotal, order_status) 
values(4, '2024-09-22', 2, 1, 15000.00, 15000.00, 'Cancel');

insert into orders(user_id, order_date, product_id, quantity, unit_price, subtotal, order_status) 
values(5, '2024-09-23', 5, 4, 8000.00, 32000.00, 'Pending');

 MVC + 自訂框架
  
  request   +----------------+             +----------------+          +------------+
 ---------> | OrderServlet   | ----------> | OrderService   | -------> | OrderDao   | ------->    MySQL
            |  (Controller)  | <---------- |                | <------- |            | <------- (web.orders)
  			+----------------+  OrderDto   +----------------+  Order   +------------+
  			       |              (Dto)                       (Entity)
  			       |
  			       v
  			+-------------+
 <--------- | order.jsp   |
  response	|    (View)   |
  			+-------------+                 
 
 下單頁面: GET  /order
 執行下單: POST /order
 
 * */

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/order.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] ids = req.getParameterValues("id");
		String[] amounts = req.getParameterValues("amount");
		
		resp.getWriter().println("ids: " + Arrays.toString(ids));
		resp.getWriter().println("amounts: " + Arrays.toString(amounts));
	}
	
}
