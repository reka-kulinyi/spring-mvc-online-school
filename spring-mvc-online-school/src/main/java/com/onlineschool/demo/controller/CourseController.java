package com.onlineschool.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineschool.demo.course.SchoolCourse;
import com.onlineschool.demo.entity.Subject;
import com.onlineschool.demo.entity.User;
import com.onlineschool.demo.service.CourseService;
import com.onlineschool.demo.service.SubjectService;
import com.onlineschool.demo.service.UserService;

@Controller
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubjectService subjectService;
	
	@GetMapping("/addNewCourse")
	public String showNewCourseForm(@RequestParam("userId") long userId, Model model) {
		User courseInstructor = userService.getInstructorById(userId);
		model.addAttribute("courseInstructor", courseInstructor);
		
		List<Subject> subjects = subjectService.getAllSubjects();
		model.addAttribute("subjects", subjects);
		model.addAttribute("schoolCourse", new SchoolCourse());
		return "course-form";
	}
	
	@PostMapping("/saveCourse")
	public String saveCourse(@Valid @ModelAttribute("schoolCourse") SchoolCourse schoolCourse, 
			BindingResult bindingResult, Model model) {
		
		User instructor = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			String username = authentication.getName();
			instructor = userService.findByUsername(username);
			model.addAttribute("instructor", instructor);
		}
		
		if(bindingResult.hasErrors()) {
			return "redirect:/courses/addNewCourse?userId=" + instructor.getId();
		}
		System.out.println("ok");
		courseService.saveCourse(schoolCourse, instructor);
		return "redirect:/instructors?instructorId=" + instructor.getId();
	}
	
	@GetMapping("/delete")
	public String deleteCourseById(@RequestParam("courseId")long courseId, 
			@RequestHeader(value = "Referer", required = false) final String referer) {
		
		courseService.deleteCourseById(courseId);
		return "redirect:"+ referer;
	}
}
