package com.onlineschool.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineschool.demo.entity.Course;
import com.onlineschool.demo.entity.Subject;
import com.onlineschool.demo.entity.User;
import com.onlineschool.demo.service.CourseService;
import com.onlineschool.demo.service.SubjectService;
import com.onlineschool.demo.service.UserService;

@Controller
public class DemoController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired 
	private SubjectService subjectService;
	
	@GetMapping("/")
	public String home(Model model) {
		
		List<User> instructors = userService.getAllInstructors();
		for(User instructor : instructors) {
			List<Course> courses = courseService.getCoursesByInstructor(instructor);
			instructor.setCourses(courses);
		}
		List<Subject> subjects = subjectService.getAllSubjects();
		model.addAttribute("instructors", instructors);
		model.addAttribute("subjects", subjects);
		return "home";
	}
	
	@GetMapping("/instructors")
	public String showInstructorProfile(@RequestParam("instructorId")long id, Model model) {
		User instructor = userService.getInstructorById(id);
		List<Course> courses = courseService.getCoursesByInstructor(instructor);
		instructor.setCourses(courses);
		model.addAttribute("instructor", instructor);
		return "instructor-profile-page";
	}
	
	@GetMapping("/search")
	public String searchInstructorsBySubjectAndPrice(
			@RequestParam("searchSubject")String searchSubject, 
			@RequestParam("maxPrice")double maxPrice, Model model) {
		
		List<User> instructors = userService.getInstructorsBySubjectAndPrice(searchSubject, maxPrice);
		for(User teacher : instructors) {
			List<Course> courses = courseService.getCoursesByInstructor(teacher);
			teacher.setCourses(courses);
		}
		model.addAttribute("instructors", instructors);
		return "instructor-list";
	} 
}
