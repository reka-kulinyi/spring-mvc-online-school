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
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineschool.demo.entity.Course;
import com.onlineschool.demo.entity.Review;
import com.onlineschool.demo.entity.Subject;
import com.onlineschool.demo.entity.User;
import com.onlineschool.demo.service.CourseService;
import com.onlineschool.demo.service.ReviewService;
import com.onlineschool.demo.service.SubjectService;
import com.onlineschool.demo.service.UserService;
import com.onlineschool.demo.user.SchoolUserForUpdate;
import java.util.logging.Logger;

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
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@GetMapping("/")
	public String home(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			String username = authentication.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("currentUser", user);
		}
		
		List<User> instructors = userService.getNewestInstructors(4);
		List<Subject> subjects = subjectService.getAllSubjects();
		List<Review> recentReviews = reviewService.getRecentReviews(4);
		model.addAttribute("recentReviews", recentReviews);
		model.addAttribute("instructors", instructors);
		model.addAttribute("subjects", subjects);
		return "home";
	}
	
	@GetMapping("/instructors")
	public String showInstructorProfile(@RequestParam("instructorId")long id, Model model){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			String username = authentication.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("currentUser", user);
		}
		
		User instructor = userService.getInstructorById(id);
		List<Review> reviews = reviewService.getReviewsByInstructor(id);
		model.addAttribute("instructor", instructor);
		model.addAttribute("reviews", reviews);
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
	
	@GetMapping("/instructors/updateForm")
	public String showUpdateForm(@RequestParam("userId") long userId, Model model) {
		
		User user = userService.getUserById(userId);
		
		// creating DTO
		SchoolUserForUpdate userForUpdate = new SchoolUserForUpdate();
		userForUpdate.setId(userId);
		userForUpdate.setEmail(user.getEmail());
		userForUpdate.setFirstName(user.getFirstName());
		userForUpdate.setLastName(user.getLastName());
		userForUpdate.setUsername(user.getUsername());
		userForUpdate.setIntroduction(user.getIntroduction());
		model.addAttribute("schoolUserForUpdate", userForUpdate);
		return "user-update-form";
	}
	
	@PostMapping("/users/update/{id}")
	public String processUserUpdate(@Valid @ModelAttribute("schoolUserForUpdate")
			SchoolUserForUpdate userForUpdate, BindingResult bindingResult, Model model) {
		
		User userToUpdate = userService.getUserById(userForUpdate.getId());
		String originalUsername = userToUpdate.getUsername();
		String username = userForUpdate.getUsername();
		logger.info("Processing update form for " + originalUsername);
		
		if(bindingResult.hasErrors()) {
			return "instructor-update-form";
		}
		
		userService.save(userForUpdate, userToUpdate);
		logger.info("Successfully updated user: " + username);
		
		if(!username.equals(originalUsername)) {
			return "re-login";
		}
		return "update-confirmation";
	}
}
