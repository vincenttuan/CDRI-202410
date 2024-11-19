package com.example.tx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tx.entity.BookInventory;

@Repository
public interface BookInventoryRepository extends JpaRepository<BookInventory, Integer> {
	// 取得書本庫存數量
	@Query(value = "select book_amount from book_inventory where book_id = :bookId", nativeQuery = true)
	Integer getBookAmount(Integer bookId);
	
	@Modifying
	// 更新庫存(目前存量 - amountToReduce)
	@Query(value = "update book_inventory set book_amount = book_amount - :amountToReduce where book_id = :bookId", nativeQuery = true)
	void updateBookAmount(Integer amountToReduce, Integer bookId);
	
}
