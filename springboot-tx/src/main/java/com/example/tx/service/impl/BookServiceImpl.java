package com.example.tx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tx.exception.InsufficientAmount;
import com.example.tx.repository.BookInventoryRepository;
import com.example.tx.repository.BookRepository;
import com.example.tx.repository.WalletRepository;
import com.example.tx.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookInventoryRepository bookInventoryRepository;
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Override
	public Integer getBookPrice(Integer bookId) {
		return bookRepository.getBookPrice(bookId);
	}

	@Override
	public Integer getBookAmount(Integer bookId) {
		return bookInventoryRepository.getBookAmount(bookId);
	}

	@Override
	public Integer getWalletBalance(String username) {
		return walletRepository.getWalletBalance(username);
	}

	@Override
	public void reduceBookAmount(Integer bookId, Integer amountToReduce)  {
		// 1. 檢查庫存
		Integer bookAmount = getBookAmount(bookId);
		if(bookAmount < amountToReduce) {
			throw new RuntimeException(String.format("bookId: %d 庫存不足 (%d < %d)%n", bookId, bookAmount, amountToReduce));
		}
		// 2. 更新庫存
		bookInventoryRepository.updateBookAmount(amountToReduce, bookId);
	}

	@Override
	public void reduceWalletBalance(String username, Integer bookPrice) throws InsufficientAmount {
		// 1. 檢查餘額
		Integer walletBalance = getWalletBalance(username);
		if(walletBalance < bookPrice) {
			throw new InsufficientAmount(String.format("username: %s 餘額不足 (%d < %d)%n", username, walletBalance, bookPrice));
		}
		// 2. 更新餘額
		walletRepository.updateWalletBalance(bookPrice, username);
	}
	
}
