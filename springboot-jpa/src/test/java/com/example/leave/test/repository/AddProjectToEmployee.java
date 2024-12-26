package com.example.leave.test.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.leave.model.entity.Employee;
import com.example.leave.model.entity.Project;
import com.example.leave.model.entity.Salary;
import com.example.leave.repository.EmployeeRepository;
import com.example.leave.repository.ProjectRepository;
import com.example.leave.repository.SalaryRepository;

@SpringBootTest
public class AddProjectToEmployee {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	public void test() {
		Project p1 = projectRepository.findById(1).get();
		Project p2 = projectRepository.findById(2).get();
		Project p3 = projectRepository.findById(3).get();
		
		Employee emp = employeeRepository.findById(1).get();
		emp.getProjects().add(p1);
		emp.getProjects().add(p2);
		emp.getProjects().add(p3);
		
		employeeRepository.save(emp);
		
		
	}
	
}
