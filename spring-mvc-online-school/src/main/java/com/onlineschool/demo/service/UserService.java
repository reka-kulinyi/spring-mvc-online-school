package com.onlineschool.demo.service;
import java.util.List;

import com.onlineschool.demo.entity.User;

public interface UserService {

	List<User> getAllInstructors();
	User getInstructorById(long id);
	List<User> getInstructorsBySubjectAndPrice(String searchSubject, double maxPrice);

}
