package com.example.tx.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "leave_request")
@Data
public class LeaveRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@Temporal(TemporalType.DATE) // 僅存日期
	private Date startDate;
	
	@Column
	@Temporal(TemporalType.DATE) // 僅存日期
	private Date endDate;
	
	@Column
	private String reason;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;

	@Override
	public String toString() {
		return "LeaveRequest [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", reason=" + reason
				+ "]";
	}
	
	
	
}
