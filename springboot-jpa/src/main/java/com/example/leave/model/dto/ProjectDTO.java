package com.example.leave.model.dto;

import lombok.Data;

@Data
public class ProjectDTO {
	private Integer id;
	private String name;
	
	@Override
	public String toString() {
		return name + " ";
	}
}
