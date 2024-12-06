package com.example.demo.controller;

import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.exception.RoomAlreadyExistsException;
import com.example.demo.exception.RoomException;
import com.example.demo.model.dto.RoomDto;
import com.example.demo.service.RoomService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * Method URI            功能
 * --------------------------------------------------------------------
 * GET    /rooms                查詢所有會議室(多筆)
 * GET    /room/{roomId}        查詢指定會議室(單筆)
 * POST   /room                 新增會議室
 * POST   /room/update/{roomId} 完整修改會議室(同時修改 roomName 與 roomSize)
 * GET    /room/delete/{roomId} 刪除會議室
 * --------------------------------------------------------------------
 * */

@Controller
@RequestMapping(value = {"/room", "/rooms"})
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping
	public String getRooms(Model model, @ModelAttribute RoomDto roomDto) {
		List<RoomDto> roomDtos = roomService.getAllRooms();
		//model.addAttribute("roomDto", new RoomDto());
		model.addAttribute("roomDtos", roomDtos);
		return "room/room";
	}
	
	@PostMapping
	// @Valid 進行驗證
	// BindingResult 驗證結果
	public String addRoom(@Valid @ModelAttribute RoomDto roomDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) { // 若有錯誤發生
			model.addAttribute("roomDtos", roomService.getAllRooms());
			return "room/room"; // 會自動將錯誤訊息傳給 jsp
		}
		roomService.addRoom(roomDto);
		return "redirect:/rooms"; // 重導到 /rooms 頁面
	}
	
	@GetMapping("/delete/{roomId}")
	public String deleteRoom(@PathVariable Integer roomId) {
		roomService.deleteRoom(roomId);
		return "redirect:/rooms"; // 重導到 /rooms 頁面
	}
	
	@GetMapping("/{roomId}")
	public String getRoom(@PathVariable Integer roomId, Model model) {
		RoomDto roomDto = roomService.getRoomById(roomId);
		model.addAttribute("roomDto", roomDto);
		return "room/room_update";
	}
	
	@PostMapping("/update/{roomId}")
	public String updateRoom(@PathVariable Integer roomId, @Valid @ModelAttribute RoomDto roomDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) { // 若有錯誤發生
			model.addAttribute("roomDto", roomDto); // 將原本的 roomDto 回傳
			return "room/room_update"; // 會自動將錯誤訊息傳給 jsp
		}
		roomService.updateRoom(roomId, roomDto);
		return "redirect:/rooms"; // 重導到 /rooms 頁面
	}
	
	@ExceptionHandler({RoomException.class})
	public String handleRoomException(RoomException e, Model model) {
		model.addAttribute("message", e.getMessage());
		return "error";
	}
	
	
}
