package com.example.tx.service;

public interface BookService {
	// 書本價格
	Integer getBookPrice(Integer bookId);
	
	// 書本庫存
	Integer getBookAmount(Integer bookId);
	
	// 帳戶餘額
	Integer getWalletBalance(String username);
		
}
