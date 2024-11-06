package com.example.demo.model.bean;

import lombok.Data;

@Data
public class Book {
	private String name; // 書名
	private Double price; //價格
	private Integer amount; // 數量
	private Boolean pub; // 出刊/停刊
}
