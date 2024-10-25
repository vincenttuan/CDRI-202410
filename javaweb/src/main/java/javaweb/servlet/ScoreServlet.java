package javaweb.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.IntSummaryStatistics;

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
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		
		String[] scores = req.getParameterValues("score");
		// String[] 轉 int[]
		int[] intScores = Arrays.stream(scores).mapToInt(Integer::parseInt).toArray();
		// 統計資料
		IntSummaryStatistics stat = Arrays.stream(intScores).summaryStatistics(); 
		resp.getWriter().println("scores: " + Arrays.toString(scores));
		resp.getWriter().println("sum: " + stat.getSum());
		resp.getWriter().println("avg: " + String.format("%.1f", stat.getAverage()));
		resp.getWriter().println("max: " + stat.getMax());
		resp.getWriter().println("min: " + stat.getMin());
		resp.getWriter().println("count: " + stat.getCount());
		
	}
	
}
