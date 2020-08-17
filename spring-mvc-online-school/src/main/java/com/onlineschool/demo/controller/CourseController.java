package com.onlineschool.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineschool.demo.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/delete")
	public String deleteCourseById(@RequestParam("courseId")long courseId, 
			@RequestHeader(value = "Referer", required = false) final String referer) {
		
		courseService.deleteCourseById(courseId);
		return "redirect:"+ referer;
	}
}
