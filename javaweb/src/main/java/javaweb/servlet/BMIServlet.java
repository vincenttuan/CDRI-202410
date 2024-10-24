package javaweb.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 執行網址: http://localhost:8080/javaweb/bmi?h=170&w=60
 * 得到 bmi 的資料
 * */
@WebServlet("/bmi")
public class BMIServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 計算 bmi
		double height = 170;
		double weight = 60;
		double bmi = weight / Math.pow(height/100, 2); // bmi 公式
		
		// 將資料傳給 jsp
		req.setAttribute("height", height);
		req.setAttribute("weight", weight);
		req.setAttribute("bmi", bmi);
		
		// 透過 jsp 印出資料
		req.getRequestDispatcher("/WEB-INF/view/bmi.jsp").forward(req, resp);
		
	}
	
}
