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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.PutExchange;

import com.example.leave.model.dto.EmployeeDTO;
import com.example.leave.model.dto.LeaveRequestDTO;
import com.example.leave.model.dto.ProjectDTO;
import com.example.leave.service.EmployeeService;
import com.example.leave.service.LeaveRequestService;
import com.example.leave.service.ProjectService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private LeaveRequestService leaveRequestService;
	
	// 取得員工資料
	@GetMapping
	public String index(Model model) {
		List<EmployeeDTO> employees = employeeService.findAllEmployeeDTOs();
		model.addAttribute("employees", employees);
		return "leave/employee";
	}
	
	// 取得員工專案
	@GetMapping("/{employeeId}/project")
	public String employeeProject(@PathVariable Integer employeeId, Model model) {
	    EmployeeDTO employeeDTO = employeeService.getEmployeeDTOById(employeeId);
	    List<ProjectDTO> projectDTOs = projectService.findAllProjectDTOs();

	    model.addAttribute("employeeDTO", employeeDTO);
	    model.addAttribute("projectDTOs", projectDTOs);
	    model.addAttribute("_method", "post");
	    
	    return "leave/employee_project";
	}
	
	// 取得員工假單(給新增假單用)
	@GetMapping("/{employeeId}/leave_request")
	public String employeeLeaveRequest(@PathVariable Integer employeeId, Model model) {
	    EmployeeDTO employeeDTO = employeeService.getEmployeeDTOById(employeeId);
	    LeaveRequestDTO leaveRequestDTO = new LeaveRequestDTO();
	    model.addAttribute("employeeDTO", employeeDTO);
	    model.addAttribute("leaveRequestDTO", leaveRequestDTO);
	    model.addAttribute("_method", "post");
	    
	    return "leave/employee_leave_request";
	}
	
	// 取得員工假單(給修改假單用)
	@GetMapping("/{employeeId}/leave_request/{leaveRequestId}")
	public String employeeLeaveRequestUpdate(@PathVariable Integer employeeId, @PathVariable Integer leaveRequestId, Model model) {
	    EmployeeDTO employeeDTO = employeeService.getEmployeeDTOById(employeeId);
	    LeaveRequestDTO leaveRequestDTO = leaveRequestService.getLeaveRequestById(leaveRequestId);
	    model.addAttribute("employeeDTO", employeeDTO);
	    model.addAttribute("leaveRequestDTO", leaveRequestDTO);
	    model.addAttribute("_method", "put");
	    
	    return "leave/employee_leave_request";
	}
	
	// 新增員工專案
	@PostMapping("/{employeeId}/project")
	public String employeeProjectUpdate(@PathVariable Integer employeeId, @RequestParam(required = false) List<Integer> projects) {
	    employeeService.updateProject(employeeId, projects);
	    return "redirect:/employee";
	}
		
	// 新增/修改員工假單
	@RequestMapping(value = "/{employeeId}/leave_request", method = {RequestMethod.POST, RequestMethod.PUT})
	public String addOrUpdateEmployeeLeaveRequest(@PathVariable Integer employeeId, @ModelAttribute LeaveRequestDTO leaveRequestDTO) {
	    employeeService.addOrUpdateLeaveRequest(employeeId, leaveRequestDTO);
		return "redirect:/employee";
	}
	
	// 刪除員工假單
	@GetMapping("/{employeeId}/leave_request/delete/{leaveRequestId}")
	public String index(@PathVariable Integer employeeId, @PathVariable Integer leaveRequestId) {
		leaveRequestService.deleteLeaveRequest(leaveRequestId);
		return "redirect:/employee";
	}
	
	
}
