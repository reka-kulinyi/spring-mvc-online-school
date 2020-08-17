package com.onlineschool.demo.service;

import java.util.List;

import com.onlineschool.demo.entity.Course;
import com.onlineschool.demo.entity.User;

public interface CourseService {
	
	List<Course> getCoursesByInstructor(User instructor);

	void deleteCourseById(long courseId);
}
