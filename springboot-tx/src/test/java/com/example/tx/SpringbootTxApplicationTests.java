package com.example.tx;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.tx.entity.Employee;
import com.example.tx.repository.EmployeeRepository;
import com.example.tx.repository.LeaveRequestRepository;

@SpringBootTest
class SpringbootTxApplicationTests {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	LeaveRequestRepository leaveRequestRepository;
	
	@Test
	void contextLoads() {
		// 取得 Employee 資料
		//List<Employee> employees = employeeRepository.findAll();
		//System.out.println(employees.get(0).getUsername());
		//System.out.println(employees.get(0).getLeaveRequests().size());
		
		List<String> usernames = employeeRepository.queryAllNames();
		System.out.println(usernames);
	}

}
