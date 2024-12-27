package com.example.leave.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class EmployeeProjectDTO {
    private Integer id;
    private String username;
    private List<ProjectDTO> projects;
}

