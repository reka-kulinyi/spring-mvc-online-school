package com.onlineschool.demo.service;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.onlineschool.demo.entity.User;
import com.onlineschool.demo.user.SchoolUser;
import com.onlineschool.demo.user.SchoolUserForUpdate;

public interface UserService extends UserDetailsService{

	List<User> getAllInstructors();
	User getInstructorById(long id);
	List<User> getInstructorsBySubjectAndPrice(String searchSubject, double maxPrice);
	List<User> getNewestInstructors(int i);
	List<User> getInstructorsBySubject(String subjectName);
	User findByUsername(String username);
	void save(SchoolUser schooluser);
	User getUserById(long userId);
	void save(@Valid SchoolUserForUpdate userForUpdate, User userToUpdate);
}
