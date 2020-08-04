package com.onlineschool.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineschool.demo.dao.CourseDao;
import com.onlineschool.demo.entity.Course;
import com.onlineschool.demo.entity.User;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;
	
	@Override
	@Transactional
	public List<Course> getCoursesByInstructor(User instructor) {
		return courseDao.getCoursesByInstructor(instructor);
	}

}
