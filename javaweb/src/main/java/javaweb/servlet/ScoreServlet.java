package javaweb.servlet;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 分數計算與統計的 Servlet
 * 網址: /score?score=100&score=85&score=45
 * 印出總分,平均,最高,最低
 * */
@WebServlet("/score")
public class ScoreServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] scores = req.getParameterValues("score");
		resp.getWriter().println("scores: " + Arrays.toString(scores));
		
	}
	
}
