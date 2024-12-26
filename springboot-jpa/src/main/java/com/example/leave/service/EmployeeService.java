package com.example.leave.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.leave.model.dto.EmployeeDTO;
import com.example.leave.model.entity.Employee;
import com.example.leave.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

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
}
