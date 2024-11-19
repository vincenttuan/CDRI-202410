package com.example.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.tx.service.BookService;
import com.example.tx.service.BuyService;

@Component
public class TXCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	private BuyService buyService;
	
	@Override
	public void run(String... args) throws Exception {
		buyService.buyOneBook("john", 1);
		
	}

}
