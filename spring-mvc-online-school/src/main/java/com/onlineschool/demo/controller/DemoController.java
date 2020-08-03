package com.onlineschool.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.onlineschool.demo.entity.User;
import com.onlineschool.demo.service.UserService;

@Controller
public class DemoController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<User> instructors = userService.getAllInstructors();
		model.addAttribute("instructors", instructors);
		return "home";
	}
}
