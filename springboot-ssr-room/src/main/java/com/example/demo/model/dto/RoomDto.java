package com.example.demo.model.dto;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
	
	@NotNull(message = "房號不可為空")
	private Integer roomId;
	
	@NotNull(message = "房名不可為空")
	@Size(min = 2, message = "房名最少要有2個字")
	private String roomName;
	
	@NotNull(message = "人數不可為空")
	@Range(min = 1, max = 500, message = "房間人數必須介於 {min} ~ {max} 人之間")
	private Integer roomSize;
	
}
