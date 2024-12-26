package com.example.leave.model.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class EmployeeDTO {
	
	private Integer id;
	
	private String username;
	
	private Integer annualLeave; // 特休天數
	
	private Date arrivalDate; // 到職日 
	
	private List<LeaveRequestDTO> leaveRequests;
	
	private SalaryDTO salary;
	
	private List<ProjectDTO> projects; // 員工所參與的專案
	
	
}
