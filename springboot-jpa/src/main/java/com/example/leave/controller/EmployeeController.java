package com.example.leave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.leave.model.dto.EmployeeDTO;
import com.example.leave.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public String index(Model model) {
		EmployeeDTO employee = employeeService.getEmployeeDTOById(1);
		model.addAttribute("employee", employee);
		return "leave/index";
	}
	
}
