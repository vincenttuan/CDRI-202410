package javaweb.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// * 萬用字元, 表示可以添入任意字串資料
@WebServlet("/demo/*")
public class DemoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri         = req.getRequestURI();
		String url         = req.getRequestURL().toString();
		int port           = req.getServerPort();
		String contextPath = req.getContextPath();
		String servletPath = req.getServletPath();
		String pathInfo    = req.getPathInfo();
		
		resp.getWriter().println("uri: " + uri);
		resp.getWriter().println("url: " + url);
		resp.getWriter().println("port: " + port);
		resp.getWriter().println("contextPath: " + contextPath);
		resp.getWriter().println("servletPath: " + servletPath);
		resp.getWriter().println("pathInfo: " + pathInfo);
	}
	
}
