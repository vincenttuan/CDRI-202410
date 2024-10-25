package javaweb.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import javaweb.utils.Hash;
import static javaweb.utils.Hash.getHash;
import static javaweb.utils.Hash.getSalt;
/**
 * 網址: /hash?password=1234
 * 得到: 
 * 		1.hashPassword(不含鹽)
 * 		2.salt + hashPassword(password+salt)(含鹽)
 * */
@WebServlet("/hash")
public class HashServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password = req.getParameter("password");
		String hash = getHash(password);
		resp.getWriter().println("hash:" + hash);
		String salt = getSalt();
		resp.getWriter().println("salt:" + salt);
		String hashSalt = getHash(password, salt);
		resp.getWriter().println("hashSalt:" + hashSalt);
	}
	
	
	
}
