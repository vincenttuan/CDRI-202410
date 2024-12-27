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
public class EmployeeService {

	@Autowired
    private EmployeeRepository employeeRepository;

	@Autowired
    private ProjectRepository projectRepository;
	
	@Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    /**
     * 查詢所有員工並返回 EmployeeDTO 列表
     * @return List<EmployeeDTO>
     */
    public List<EmployeeDTO> findAllEmployeeDTOs() {
        // 查詢所有員工
        List<Employee> employees = employeeRepository.findAll();

        // 使用 ModelMapper 將 Employee 實體轉換為 EmployeeDTO
        return employees.stream()
        				.map(employee -> modelMapper.map(employee, EmployeeDTO.class))
        				.toList();
    }
    
    /**
     * 根據員工 ID 獲取 EmployeeDTO 資料
     * @param employeeId 員工 ID
     * @return EmployeeDTO
     */
    public EmployeeDTO getEmployeeDTOById(Integer employeeId) {
        // 查詢員工資料
        Optional<Employee> optEmployee = employeeRepository.findById(employeeId);
        if (optEmployee.isEmpty()) {
            throw new IllegalArgumentException("找不到 ID 為 " + employeeId + " 的員工");
        }

        // 將 Employee 實體轉換為 EmployeeDTO
        Employee employee = optEmployee.get();
        
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

        return employeeDTO;
    }
    
    public void updateProject(Integer employeeId, List<Integer> projectIds) {
        // 查詢員工
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("找不到 ID 為 " + employeeId + " 的員工"));

        // 處理專案為空的情況
        List<Project> projects = (projectIds == null || projectIds.isEmpty())
                ? List.of() // 若為空，設置為空集合
                : projectRepository.findAllById(projectIds);

        // 設置專案關聯
        employee.setProjects(projects);

        // 保存更新
        employeeRepository.save(employee);
    }
    
    public void addOrUpdateLeaveRequest(Integer employeeId, LeaveRequestDTO leaveRequestDTO) {
    	// 查詢員工
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("找不到 ID 為 " + employeeId + " 的員工"));
        
        LeaveRequest leaveRequest = modelMapper.map(leaveRequestDTO, LeaveRequest.class);
        
        // 設定請假單員工
        leaveRequest.setEmployee(employee);
        
        // 儲存
        leaveRequestRepository.save(leaveRequest);

    }
    
}
