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
		// 將 dto 轉 entity
		Todo todo = modelMapper.map(todoDTO, Todo.class);
		Todo savedTodo = todoRepository.save(todo);
		return modelMapper.map(savedTodo, TodoDTO.class);
	}
	
	// 更新代辦事項
	@Override
	public TodoDTO updateTodo(TodoDTO todoDTO) {
		return todoRepository.findById(todoDTO.getId())
				.map(todo -> {
					// 將 todoDTO 每一個欄位資料對應更新到 todo 欄位中
					// todoDTO.id => todo.id
					// todoDTO.text => todo.text
					// todoDTO.completed => todo.completed
					modelMapper.map(todoDTO, todo); // 更新欄位資料
					Todo updateTodo = todoRepository.save(todo);
					return modelMapper.map(updateTodo, TodoDTO.class);
				})
				.orElseThrow(() -> new RuntimeException("查無資料"));
	}
	
	// 刪除代辦事項
	@Override
	public void deleteTodo(Long id) {
		if(todoRepository.existsById(id)) { // 資料是否存在
			todoRepository.deleteById(id);
			return;
		}
		throw new RuntimeException("查無資料");
	}

}
