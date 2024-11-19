package com.example.tx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tx.entity.BookInventory;

@Repository
public interface BookInventoryRepository extends JpaRepository<BookInventory, Integer> {
	// 取得書本庫存數量
	@Query(value = "select book_amount from book_inventory where book_id = :bookId", nativeQuery = true)
	Integer getBookAmount(Integer bookId);
}
