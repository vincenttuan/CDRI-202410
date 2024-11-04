package javaweb.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javaweb.model.dto.OrderDto;
import javaweb.model.dto.UserCert;
import javaweb.service.OrderService;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	
	private OrderService orderService = new OrderService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		UserCert userCert = (UserCert)session.getAttribute("userCert");
		
		// 使用者購物車資料
		// 購物車資料指的是 order_status = "Pending"
		List<OrderDto> orderDtos = orderService.findAllOrders(userCert.getUserId(), "Pending");
		req.setAttribute("orderDtos", orderDtos);
		req.getRequestDispatcher("/WEB-INF/view/cart.jsp").forward(req, resp);
	}
	
	
}
