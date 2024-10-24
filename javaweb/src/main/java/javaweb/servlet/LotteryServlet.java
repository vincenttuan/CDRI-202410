package javaweb.servlet;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 模式: Model 1
 * Servlet 負責邏輯
 * JSP 負責資料呈現
 * 連結方式: Get
 * 執行位置: http://localhost:8080/javaweb/lottery
 * */

//@WebServlet(urlPatterns = {"/lottery"})
@WebServlet("/lottery")
public class LotteryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 產生電腦選號(四星彩)
		Random random = new Random();
		int n1 = random.nextInt(10); // 0~9 的隨機數
		int n2 = random.nextInt(10); // 0~9 的隨機數
		int n3 = random.nextInt(10); // 0~9 的隨機數
		int n4 = random.nextInt(10); // 0~9 的隨機數
		
		// 利用 req.setAttribute() 將資料傳遞給 jsp
		req.setAttribute("n1", n1);
		req.setAttribute("n2", n2);
		req.setAttribute("n3", n3);
		req.setAttribute("n4", n4);
		
		// 利用 RequestDispatcher 重導到指定 jsp
		req.getRequestDispatcher("/WEB-INF/view/lottery.jsp").forward(req, resp);
		
		
	}
	
}
