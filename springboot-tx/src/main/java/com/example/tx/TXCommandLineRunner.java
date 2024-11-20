package com.example.tx;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.tx.entity.Book;
import com.example.tx.entity.BookInventory;
import com.example.tx.exception.InsufficientAmount;
import com.example.tx.repository.BookInventoryRepository;
import com.example.tx.repository.BookRepository;
import com.example.tx.service.BookService;
import com.example.tx.service.BuyService;

import jakarta.persistence.EntityManager;

@Component
public class TXCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	private BuyService buyService;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public void run(String... args) throws Exception {
		buyBook("john", 1);
		
		//updateBookName(1, "C#");
	}
	
	// 修改書名
	private void updateBookName(Integer bookId, String newBookName) {
		Optional<Book> optBook = bookRepository.findById(bookId);
		if(optBook.isEmpty()) {
			return;
		}
		// 取得 book 實體 (Managed Entity: entity 會與資料表保持同步)
		Book book = optBook.get();
		System.out.println("修改前:" + book);
		// 修改 book name
		book.setBookName(newBookName);
		// 手動儲存 (Managed Entity: 不需要手動儲存)
		//bookRepository.save(book);
		
		System.out.println("修改後:" + book);
		// 重抓資料庫的資訊, 確認是否已經改
		System.out.println("資料庫:" + bookRepository.findById(bookId).get());
		
	}
	
	// 買書
	private void buyBook(String username, Integer bookId) {
		try {
			buyService.buyOneBook(username, bookId);
			System.out.println("買書成功");
		} catch (InsufficientAmount e) {
			System.out.println(e.getMessage());
			System.out.println("買書失敗");
		}
		
	}
	
}
