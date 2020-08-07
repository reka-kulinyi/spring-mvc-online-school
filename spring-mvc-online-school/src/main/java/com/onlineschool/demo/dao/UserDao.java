package com.onlineschool.demo.dao;
import java.math.BigDecimal;
import java.util.List;

import com.onlineschool.demo.entity.User;

public interface UserDao {

	List<User> getAllInstructors();

	User getInstructorById(long id);

	List<User> getInstructorsBySubjectAndPrice(String searchSubject, BigDecimal maxPrice);

	List<User> getNewestInstructors(int i);
}
