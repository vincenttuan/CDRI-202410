package com.example.cart.aop;

/**
 * 切面類別：UserSessionCheckAspect
 *
 * 此類別負責檢查所有被 @CheckUserSession 註解標記的方法是否符合用戶會話的條件。
 * 主要作用是攔截這些方法的執行，確保用戶已登入且會話有效，否則阻止方法執行。
 *
 * 功能：
 * - 在方法執行之前，檢查 HttpSession 中是否有 "userDTO"（用戶資訊）。
 * - 若無 "userDTO" 或會話過期，拋出 UnauthorizedException，返回未授權錯誤。
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
public class UserSessionCheckAspect {

}
