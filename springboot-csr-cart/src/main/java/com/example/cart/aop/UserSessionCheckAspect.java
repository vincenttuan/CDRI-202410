package com.example.cart.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cart.exception.UnauthorizedException;
import com.example.cart.model.dto.UserDTO;

import jakarta.servlet.http.HttpSession;

/**
 * 切面類別：UserSessionCheckAspect
 *
 * 此類別負責檢查所有被 @CheckUserSession 註解標記的方法是否符合用戶會話session的條件。
 * 主要作用是攔截這些方法的執行，確保用戶已登入且會話session有效，否則阻止方法執行。
 *
 * 功能：
 * - 在方法執行之前，檢查 HttpSession 中是否有 "userDTO"（用戶資訊）。
 * - 若無 "userDTO" 或會話session過期，拋出 UnauthorizedException，返回未授權錯誤。
 *
 * AOP 設定：
 * - @Aspect：標記此類別為切面類別。
 * - @Before：指定在被 @CheckUserSession 標記的方法執行前，執行檢查邏輯。
 * 
 * 適用場景：
 * - 所有需要用戶登入才能執行的方法，例如訂單查詢、資料修改等。
 * 
 * 使用方式：
 * - 方法上加上 @CheckUserSession 註解，AOP 會自動攔截並檢查。
 * */
@Aspect // 宣告此類別為 AOP 切面
@Component // 由 Spring 來管理此物件
public class UserSessionCheckAspect {
	@Autowired
	private HttpSession session; // 自動注入 HttpSession
	
	@Before("@annotation(com.example.cart.aop.CheckUserSession)")
	public void checkUserSession() throws UnauthorizedException {
		// 取得 user 資訊
		UserDTO userDTO = (UserDTO)session.getAttribute("userDTO");
		// 檢查用戶是否已經登入
		if(userDTO == null) {
			// 未登入, 拋出未授權例外
			throw new UnauthorizedException("未登入或登入已過期");
		}
	}
	
}









