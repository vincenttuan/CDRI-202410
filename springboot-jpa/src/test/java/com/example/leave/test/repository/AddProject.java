package com.example.leave.test.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.leave.model.entity.Project;
import com.example.leave.model.entity.Salary;
import com.example.leave.repository.ProjectRepository;
import com.example.leave.repository.SalaryRepository;

@SpringBootTest
public class AddProject {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Test
	public void test() {
		Project project = new Project();
		project.setName("Web");
		projectRepository.save(project);
		
	}
	
}
