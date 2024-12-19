package com.example.tx;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.tx.dto.UsernameLeaveDaysDTO;
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
		
		//List<String> usernames = employeeRepository.queryAllNames();
		//System.out.println(usernames);
		
		List<Object[]> usernameLeaveDaysDTOs = employeeRepository.queryUsernamesWithLeaveDays();
		System.out.println(usernameLeaveDaysDTOs);
		
		List<UsernameLeaveDaysDTO> dtoList = usernameLeaveDaysDTOs.stream()
				.map(row -> new UsernameLeaveDaysDTO((String)row[0], ((Number) row[1]).intValue()))
				.toList();
		System.out.println(dtoList);
		
		dtoList.forEach(dto -> {
			System.out.println(dto.getUsername() + ": " + dto.getTotalLeaveDays());
		});
				
	}

}





