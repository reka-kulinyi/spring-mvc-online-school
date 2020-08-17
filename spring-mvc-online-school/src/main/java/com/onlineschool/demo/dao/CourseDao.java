package com.onlineschool.demo.dao;

import java.util.List;

import com.onlineschool.demo.entity.Course;
import com.onlineschool.demo.entity.User;

public interface CourseDao {
	List<Course> getCoursesByInstructor(User instructor);

	void deleteCourseById(long courseId);
}
