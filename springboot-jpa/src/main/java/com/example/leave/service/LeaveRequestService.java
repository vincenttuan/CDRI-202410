package com.example.leave.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.leave.model.dto.EmployeeDTO;
import com.example.leave.model.dto.LeaveRequestDTO;
import com.example.leave.model.dto.ProjectDTO;
import com.example.leave.model.entity.Employee;
import com.example.leave.model.entity.LeaveRequest;
import com.example.leave.model.entity.Project;
import com.example.leave.repository.EmployeeRepository;
import com.example.leave.repository.LeaveRequestRepository;
import com.example.leave.repository.ProjectRepository;

@Service
public class LeaveRequestService {

	@Autowired
    private EmployeeRepository employeeRepository;

	@Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    public void addLeaveRequest(LeaveRequest leaveRequest) {
    	// 儲存
        leaveRequestRepository.save(leaveRequest);
    }
    
    public void deleteLeaveRequest(Integer id) {
    	// 刪除
    	leaveRequestRepository.deleteById(id);
    }
    

}
