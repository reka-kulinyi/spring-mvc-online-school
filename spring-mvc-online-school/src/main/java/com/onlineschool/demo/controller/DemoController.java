package com.onlineschool.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineschool.demo.entity.Course;
import com.onlineschool.demo.entity.Review;
import com.onlineschool.demo.entity.Subject;
import com.onlineschool.demo.entity.User;
import com.onlineschool.demo.service.CourseService;
import com.onlineschool.demo.service.ReviewService;
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
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/")
	public String home(Model model) {
		
		List<User> instructors = userService.getNewestInstructors(4);
		List<Subject> subjects = subjectService.getAllSubjects();
		List<Review> recentReviews = reviewService.getRecentReviews(4);
		model.addAttribute("recentReviews", recentReviews);
		model.addAttribute("instructors", instructors);
		model.addAttribute("subjects", subjects);
		return "home";
	}
	
	@GetMapping("/instructors")
	public String showInstructorProfile(@RequestParam("instructorId")long id, Model model) {
		User instructor = userService.getInstructorById(id);
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
	
	@GetMapping("instructors/all")
	public String showAllInstructors(Model model) {
		List<User> instructors = userService.getAllInstructors();
		model.addAttribute("instructors", instructors);
		return "instructors";
	}
	
	@GetMapping("subjects/all")
	public String showAllSubjects(Model model) {
		List<Subject> allSubjects = subjectService.getAllSubjects();
		model.addAttribute("subjects", allSubjects);
		return "subjects";
	}
	
	@GetMapping("/instructors/subjects")
	public String showInstructorsBySubject(@RequestParam("subjectName")String subjectName,
			Model model) {
		List<User> instructorsBySubject = userService.getInstructorsBySubject(subjectName);
		model.addAttribute("instructors", instructorsBySubject);
		return "instructor-list";
	}
}
