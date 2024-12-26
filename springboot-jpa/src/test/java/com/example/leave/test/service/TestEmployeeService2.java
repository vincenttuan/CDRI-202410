package com.example.leave.test.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.leave.model.dto.EmployeeDTO;
import com.example.leave.model.dto.LeaveRequestDTO;
import com.example.leave.model.dto.ProjectDTO;
import com.example.leave.model.entity.LeaveRequest;
import com.example.leave.service.EmployeeService;

@SpringBootTest
public class TestEmployeeService2 {
	@Autowired
	EmployeeService employeeService;
	
	@Test
	public void test() {
		try {
			List<EmployeeDTO> employees = employeeService.findAllEmployeeDTOs();
			employees.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
