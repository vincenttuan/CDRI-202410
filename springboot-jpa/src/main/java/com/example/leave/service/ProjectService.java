package com.example.leave.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.leave.model.dto.ProjectDTO;
import com.example.leave.repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    public List<ProjectDTO> findAllProjectDTOs() {
        // 查詢所有專案
        return projectRepository.findAll().stream()
        				.map(project -> modelMapper.map(project, ProjectDTO.class))
        				.toList();
    }
    
}
