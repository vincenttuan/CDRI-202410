package javaweb.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javaweb.exception.CertException;
import javaweb.model.dto.UserCert;
import javaweb.service.CertService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private CertService certService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 重導到 login.jsp
		req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		// 驗證帳密並取得憑證
		UserCert userCert = null;
		
		try {
			userCert = certService.getCert(username, password);
		} catch (CertException e) {
			// 將錯誤丟給(重導) error.jsp
			req.setAttribute("message", e.getMessage());
			req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
			return;
		}
		
		// 將憑證放入 session 變數中以利其他程式進行取用與驗證
		HttpSession session = req.getSession();
		session.setAttribute("userCert", userCert);
		req.setAttribute("message", "登入成功");
		req.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(req, resp);
	}
	
}
