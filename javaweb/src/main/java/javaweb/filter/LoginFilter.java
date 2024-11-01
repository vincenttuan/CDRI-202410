package javaweb.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/*"}) // 設定要過濾/攔截的路徑
public class LoginFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 編碼
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=utf-8");
		
		System.out.println("攔截過濾 URL :" + request.getRequestURL());
		response.getWriter().print("攔截過濾 URL :" + request.getRequestURL());
		response.getWriter().print("今日公休");
	}
	
}
