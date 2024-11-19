package com.example.tx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tx.service.BookService;
import com.example.tx.service.BuyService;

@Service
public class BuyServiceImpl implements BuyService {
	
	@Autowired
	private BookService bookService;
	
	@Override
	public void buyOneBook(String username, Integer bookId) {
		// 1. 查詢書本價格
		
		// 2. 減去庫存
		
		// 3. 修改餘額
		
		// 4. 其他處理
		
	}

}
