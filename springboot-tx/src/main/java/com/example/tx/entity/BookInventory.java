package com.example.tx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInventory {
	@Id
	private Integer bookId; // 與書本的 ID 對應
	private Integer bookAmount; // 庫存數量
}
