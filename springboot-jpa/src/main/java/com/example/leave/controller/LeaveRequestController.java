package com.example.leave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.leave.model.dto.EmployeeDTO;
import com.example.leave.model.dto.ProjectDTO;
import com.example.leave.service.EmployeeService;
import com.example.leave.service.LeaveRequestService;
import com.example.leave.service.ProjectService;

@Controller
@RequestMapping("/leave_request")
public class LeaveRequestController {
	@Autowired
	private LeaveRequestService leaveRequestService;
	
	@GetMapping("/delete/{id}")
	public String index(@PathVariable Integer id) {
		leaveRequestService.deleteLeaveRequest(id);
		return "redirect:/employee";
	}
	
}
