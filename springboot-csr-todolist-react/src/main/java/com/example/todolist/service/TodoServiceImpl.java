package com.example.todolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todolist.model.dto.TodoDTO;

@Service
public class TodoServiceImpl implements TodoService {
	
	// 取得所有代辦事項
	@Override
	public List<TodoDTO> getAllTodos() {
		// TODO Auto-generated method stub
		return null;
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
