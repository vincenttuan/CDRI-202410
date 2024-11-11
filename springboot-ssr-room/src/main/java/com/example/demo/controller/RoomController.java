package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.dto.RoomDto;
import com.example.demo.service.RoomService;

/**
 * Method URI            功能
 * --------------------------------------------------------------------
 * GET    /rooms         查詢所有會議室(多筆)
 * GET    /room/{roomId} 查詢指定會議室(單筆)
 * POST   /room          新增會議室
 * PUT    /room/{roomId} 完整修改會議室(同時修改 roomName 與 roomSize)
 * PATCH  /room/{roomId} 部分欄位修改會議室(只修改  roomName 或 roomSize etc…)
 * DETETE /room/{roomId} 刪除會議室
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
		return "room";
	}
	
	@PostMapping
	public String addRoom(RoomDto roomDto) {
		roomService.addRoom(roomDto);
		return "redirect:/rooms"; // 重導到 /rooms 頁面
	}
	
	
	
}
