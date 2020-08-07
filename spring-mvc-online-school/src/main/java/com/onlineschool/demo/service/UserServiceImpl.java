package com.onlineschool.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineschool.demo.dao.UserDao;
import com.onlineschool.demo.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public List<User> getAllInstructors() {
		return userDao.getAllInstructors();
	}

	@Override
	@Transactional
	public User getInstructorById(long id) {
		return userDao.getInstructorById(id);
	}

	@Override
	@Transactional
	public List<User> getInstructorsBySubjectAndPrice(String searchSubject, double maxPrice) {
		return userDao.getInstructorsBySubjectAndPrice(searchSubject, maxPrice);
	}
	
	
}
