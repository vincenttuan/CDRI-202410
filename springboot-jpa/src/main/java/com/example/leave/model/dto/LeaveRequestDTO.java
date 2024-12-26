package com.example.leave.model.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import lombok.Data;

@Data
public class LeaveRequestDTO {
	
	private Integer id;
	
	private String type; // 特休, 病假, 事假
	
	private Date startDate;
	
	private Date endDate;
	
	private String reason;
	
	private String status = "PENDING"; // PENDING、APPROVED、REJECTED

	public long getLeaveDays() {
		long leaveDays = Math.abs(((endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24)));
		leaveDays += 1;
		return leaveDays;
    }
}
