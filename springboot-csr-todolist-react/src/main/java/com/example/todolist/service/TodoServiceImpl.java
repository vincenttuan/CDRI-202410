package com.example.todolist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.todolist.repository.TodoRepository;
import com.example.todolist.model.dto.TodoDTO;
import com.example.todolist.model.entity.Todo;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoRepository todoRepository; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	// 取得所有代辦事項
	@Override
	public List<TodoDTO> getAllTodos() {
		List<Todo> todos = todoRepository.findAll();
		return todos.stream()
				.map(todo -> modelMapper.map(todo, TodoDTO.class))
				.collect(Collectors.toList());
	}
	
	// 新增代辦事項
	@Override
	public TodoDTO createTodo(TodoDTO todoDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 更新代辦事項
	@Override
	public TodoDTO updateTodo(TodoDTO todoDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 刪除代辦事項
	@Override
	public void deleteTodo(Long id) {
		// TODO Auto-generated method stub
		
	}

}
