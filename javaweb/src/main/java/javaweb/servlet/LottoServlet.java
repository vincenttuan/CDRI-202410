package javaweb.servlet;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 模式: Model 0
 * 連結方式: Get 
 * 執行位置: http://localhost:8080/javaweb/lotto
 * 執行位置: http://localhost:8080/javaweb/lucky
 * http -> 通訊協定
 * localhost -> 主機名稱
 * 8080 -> port
 * /javaweb -> context path
 * /lotto -> servlet path
 * /lucky -> servlet path
 * 無      -> path info
*/
@WebServlet(urlPatterns = {"/lotto", "/lucky"})
public class LottoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 產生電腦選號(四星彩)
		Random random = new Random();
		int n1 = random.nextInt(10); // 0~9 的隨機數
		int n2 = random.nextInt(10); // 0~9 的隨機數
		int n3 = random.nextInt(10); // 0~9 的隨機數
		int n4 = random.nextInt(10); // 0~9 的隨機數
		
		// 印出(利用 resp 回傳給瀏覽器)
		resp.getWriter().print("<html>");
		resp.getWriter().print("<H1>");
		resp.getWriter().print(n1);
		resp.getWriter().print(n2);
		resp.getWriter().print(n3);
		resp.getWriter().print(n4);
		resp.getWriter().print("</H1>");
		resp.getWriter().print("</html>");
	}
	
}
