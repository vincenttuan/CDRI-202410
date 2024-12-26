package com.example.leave.model.dto;

import java.util.Date;
import java.util.List;

import com.example.leave.model.entity.LeaveRequest;
import com.example.leave.model.entity.Project;
import com.example.leave.model.entity.Salary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

public class EmployeeDTO {
	
	private Integer id;
	
	private String username;
	
	private Integer annualLeave; // 特休天數
	
	private Date arrivalDate; // 到職日 
	
	private List<LeaveRequest> leaveRequests;
	
	private Salary salary;
	
	private List<Project> projects; // 員工所參與的專案
	
	
}
