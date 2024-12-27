package com.example.leave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.leave.model.dto.EmployeeDTO;
import com.example.leave.model.dto.EmployeeProjectDTO;
import com.example.leave.model.dto.ProjectDTO;
import com.example.leave.service.EmployeeService;
import com.example.leave.service.ProjectService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping
	public String index(Model model) {
		List<EmployeeDTO> employees = employeeService.findAllEmployeeDTOs();
		model.addAttribute("employees", employees);
		return "leave/employee";
	}
	
	@GetMapping("/{employeeId}/project")
	public String employeeProject(@PathVariable Integer employeeId, Model model) {
	    EmployeeProjectDTO employeeProjectDTO = employeeService.getEmployeeProjectDTOById(employeeId);
	    List<ProjectDTO> projectDTOs = projectService.findAllProjectDTOs();

	    model.addAttribute("employeeProjectDTO", employeeProjectDTO);
	    model.addAttribute("projectDTOs", projectDTOs);

	    return "leave/employee_project";
	}

	@PostMapping("/{employeeId}/project")
	public String employeeProjectUpdate(@PathVariable Integer employeeId, @RequestParam(required = false) List<Integer> projects) {
	    employeeService.updateProject(employeeId, projects);
	    return "redirect:/employee";
	}
	
}
