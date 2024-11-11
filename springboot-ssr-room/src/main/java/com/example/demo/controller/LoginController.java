package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.dto.UserCert;
import com.example.demo.service.CertService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private CertService certService;
	
	@GetMapping
	public String loginPage() {
		return "login";
	}
	
	@PostMapping
	public String checkLogin(@RequestParam String username, @RequestParam String password, HttpSession session, HttpServletRequest req, Model model) {
		// 透過 userService 來確認登入
		// 驗證帳密並取得憑證
		UserCert userCert = null;
		
		try {
			userCert = certService.getCert(username, password);
		} catch (Exception e) {
			// 將錯誤丟給(重導) error.jsp
			model.addAttribute("message", e.getMessage());
			return "error";
		}
		
		// 將憑證放入 session 變數中以利其他程式進行取用與驗證
		session.setAttribute("userCert", userCert); // 放憑證
		session.setAttribute("locale", req.getLocale()); // 取得客戶端所在地 例如: zh_TW
		return "redirect:/rooms"; // 登入成功後到首頁
	}
	
}
