package com.onlineschool.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.onlineschool.demo.entity.Course;
import com.onlineschool.demo.entity.User;
import com.onlineschool.demo.service.CourseService;
import com.onlineschool.demo.service.UserService;

@Controller
public class DemoController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<User> instructors = userService.getAllInstructors();
		for(User instructor : instructors) {
			List<Course> courses = courseService.getCoursesByInstructor(instructor);
			instructor.setCourses(courses);
		}
		model.addAttribute("instructors", instructors);
		return "home";
	}
}
