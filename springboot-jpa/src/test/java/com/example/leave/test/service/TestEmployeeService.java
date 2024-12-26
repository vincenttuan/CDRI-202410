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
public class TestEmployeeService {
	@Autowired
	EmployeeService employeeService;
	
	@Test
	public void test() {
		try {
			EmployeeDTO employeeDTO = employeeService.getEmployeeDTOById(1);
			System.out.println(employeeDTO.getUsername());
			List<LeaveRequestDTO> leaveRequests = employeeDTO.getLeaveRequests();
			leaveRequests.forEach(leave -> {
				System.out.println(leave.getReason());
			});
			List<ProjectDTO> projects = employeeDTO.getProjects();
			projects.forEach(p -> {
				System.out.println(p.getName());
			});
			
			System.out.println(employeeDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
