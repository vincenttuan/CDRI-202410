package com.example.tx.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 50, nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private Integer annualLeave;
	
	@OneToMany(mappedBy = "employee")
	private List<LeaveRequest> leaveRequests;
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", annualLeave=" + annualLeave + "]";
	}
	
	
}
