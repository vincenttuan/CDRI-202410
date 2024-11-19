package com.example.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.tx.service.BookService;

@Component
public class TXCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	private BookService bookService;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("book price: " + bookService.getBookPrice(1));
		System.out.println("book amount: " + bookService.getBookAmount(1));
		System.out.println("wallet balance: " + bookService.getWalletBalance("john"));
		
	}

}
