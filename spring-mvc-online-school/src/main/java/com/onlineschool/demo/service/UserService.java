package com.onlineschool.demo.service;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.onlineschool.demo.entity.User;

public interface UserService extends UserDetailsService{

	List<User> getAllInstructors();
	User getInstructorById(long id);
	List<User> getInstructorsBySubjectAndPrice(String searchSubject, double maxPrice);
	List<User> getNewestInstructors(int i);
	List<User> getInstructorsBySubject(String subjectName);
	User findByUsername(String username);
}
