package com.example.tx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.tx.service.BookService;
import com.example.tx.service.BuyService;

@Service // 交易服務
public class BuyServiceImpl implements BuyService {
	
	@Autowired
	private BookService bookService;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void buyOneBook(String username, Integer bookId) {
		System.out.println(username + " 要買書");
		// 1. 查詢書本價格
		Integer bookPrice = bookService.getBookPrice(bookId);
		System.out.println("bookId price: " + bookPrice);
		// 2. 減去庫存(1本)
		bookService.reduceBookAmount(bookId, 1);
		// 3. 修改餘額
		bookService.reduceWalletBalance(username, bookPrice);
		// 4. 其他處理
		System.out.println(username + " 結帳完成...");
	}

}
