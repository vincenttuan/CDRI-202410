package com.example.tx.service;

import com.example.tx.exception.InsufficientAmount;

// 買書服務
public interface BuyService {
	void buyOneBook(String username, Integer bookId) throws InsufficientAmount;
}
