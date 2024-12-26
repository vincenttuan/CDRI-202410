package com.example.leave.model.dto;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class LeaveRequestDTO {
	
	private Integer id;
	
	private String type; // 特休, 病假, 事假
	
	private Date startDate;
	
	private Date endDate;
	
	private String reason;
	
	private String status = "PENDING"; // PENDING、APPROVED、REJECTED

	
}
