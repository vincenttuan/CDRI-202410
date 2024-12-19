package com.example.tx;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.tx.dto.LeaveDaysDTO;
import com.example.tx.entity.Employee;
import com.example.tx.entity.LeaveRequest;
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
		
//		List<Object[]> usernameLeaveDaysDTOs = employeeRepository.queryUsernamesWithLeaveDays();
//		System.out.println(usernameLeaveDaysDTOs);
//		
//		List<LeaveDaysDTO> dtoList = usernameLeaveDaysDTOs.stream()
//				.map(row -> new LeaveDaysDTO((String)row[0], (Integer) row[1]))
//				.toList();
//		System.out.println(dtoList);
//		
//		dtoList.forEach(dto -> {
//			System.out.println(dto.getUsername() + ": " + dto.getTotalLeaveDays());
//		});
		
		try {
			// 日期格式
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

	        // 解析日期
	        Date startDate = dateFormat.parse("2024/12/21");
	        Date endDate = dateFormat.parse("2024/12/23");
	        
			Employee employee = employeeRepository.findById(1).get();
			LeaveRequest leave = new LeaveRequest();
			leave.setEmployee(employee);
			leave.setStartDate(startDate);
			leave.setEndDate(endDate);
			leave.setReason("ok");
			
			leaveRequestRepository.save(leave);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
				
	}

}





