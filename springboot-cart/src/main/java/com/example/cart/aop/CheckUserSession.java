package com.example.cart.aop;

/**
 * 自訂註解：@CheckUserSession
 *
 * 此註解用於標記需要檢查用戶登入狀態的方法。
 * 當方法被標記此註解時，AOP 切面會在方法執行之前攔截該方法，
 * 並檢查當前用戶是否已登入（會話是否有效）。
 * 如果檢查失敗，會拋出 UnauthorizedException，阻止方法執行。
 *
 * 適用場景：
 * - 任何需要保證用戶已登入才能執行的方法。
 * - 確保業務邏輯執行之前，完成用戶身份驗證。
 *
 * 註解屬性：
 * - 目標：方法（@Target(ElementType.METHOD)）
 * - 壽命：運行時（@Retention(RetentionPolicy.RUNTIME)）
 *
 * 使用方式：
 * - 在需要檢查用戶登入狀態的方法上標記 @CheckUserSession。
 * - 確保有對應的 AOP 切面處理此註解邏輯。
 */
public class CheckUserSession {

}
