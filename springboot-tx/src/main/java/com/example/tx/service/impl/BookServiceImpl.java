package com.example.tx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
