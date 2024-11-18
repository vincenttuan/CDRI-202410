package com.example.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.model.dto.TodoDTO;
import com.example.todolist.response.ApiResponse;
import com.example.todolist.service.TodoService;

/**
 * WEB API
 * ------------------------------------------
 * servlet-path: /todolist  (@RequestMapping)
 * ------------------------------------------
 * GET    "/"     獲取所有待辦事項
 * POST   "/"     新增待辦事項
 * PUT    "/{id}" 更新待辦事項
 * DELETE "/{id}" 刪除待辦事項
 * ------------------------------------------
 * */

@RestController
@RequestMapping("/todolist")
@CrossOrigin(origins = "http://localhost:5173") // 跨域請求
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	// 獲取所有待辦事項
	@GetMapping
	public ResponseEntity<ApiResponse<List<TodoDTO>>> getAllDtos() {
		List<TodoDTO> todos = todoService.getAllTodos();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", todos));
	}
	
	// 新增待辦事項
	@PostMapping
	public ResponseEntity<ApiResponse<TodoDTO>> createTodo(@RequestBody TodoDTO todoDto) {
		TodoDTO createdTodoDTO = todoService.createTodo(todoDto);
		return ResponseEntity.ok(ApiResponse.success("新增成功", createdTodoDTO));
	}
	
	// 更新待辦事項
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<TodoDTO>> updateTodo(@PathVariable Long id, @RequestBody TodoDTO todoDto) {
		todoDto.setId(id);
		TodoDTO updatedTodoDTO = todoService.updateTodo(todoDto);
		return ResponseEntity.ok(ApiResponse.success("修改成功", updatedTodoDTO));
	}
	
	// 刪除待辦事項
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteTodo(@PathVariable Long id) {
		todoService.deleteTodo(id);
		return ResponseEntity.ok(ApiResponse.success("刪除成功", null));
	}
	
	// 處理異常狀況
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiResponse<Void>> handlTodoRuntimeException(RuntimeException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), e.getMessage()));
	}
	
}
