package com.onlineschool.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineschool.demo.entity.User;
import com.onlineschool.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/instructors")
	public List<User> getInstructors() {
		return userService.getAllInstructors();
	}

}
