package com.onlineschool.demo.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlineschool.demo.entity.User;
import com.onlineschool.demo.service.UserService;
import com.onlineschool.demo.user.SchoolUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/registrationForm")
	public String showLoginPage(Model model) {
		model.addAttribute("schoolUser", new SchoolUser());
		return "registration-page";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
			@Valid @ModelAttribute("schoolUser") SchoolUser schooluser,
			BindingResult bindingResult,
			Model model
			) {
		String username = schooluser.getUsername();
		logger.info("Processing registration form for: " + username);
		
		// form validation
		if(bindingResult.hasErrors()) {
			return "registration-page";
		}
		
		// check the database if user already exists
		User existing = userService.findByUsername(username);
		if(existing != null) {
			model.addAttribute("schoolUser", new SchoolUser());
			model.addAttribute("registrationError", "Username already exists");
			
			logger.warning("Username already exists");
			return "registration-page";
		}
		
		// create new user account
		userService.save(schooluser);
		logger.info("Successfully created user: " + username);
		return "registration-confirmation";
	}
}
