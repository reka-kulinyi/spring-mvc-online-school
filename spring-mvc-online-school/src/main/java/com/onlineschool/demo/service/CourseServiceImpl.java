package com.onlineschool.demo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineschool.demo.course.SchoolCourse;
import com.onlineschool.demo.dao.CourseDao;
import com.onlineschool.demo.dao.SubjectDao;
import com.onlineschool.demo.entity.Course;
import com.onlineschool.demo.entity.Subject;
import com.onlineschool.demo.entity.User;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private SubjectDao subjectDao;
	
	@Override
	@Transactional
	public List<Course> getCoursesByInstructor(User instructor) {
		return courseDao.getCoursesByInstructor(instructor);
	}

	@Override
	@Transactional
	public void deleteCourseById(long courseId) {
		courseDao.deleteCourseById(courseId);		
	}

	@Override
	@Transactional
	public void saveCourse(@Valid SchoolCourse schoolCourse, User instructor) {
		
		Course course = new Course();
		Subject subject = subjectDao.getSubjectByName(schoolCourse.getSubjectName());
		course.setInstructor(instructor);
		course.setSubject(subject);
		course.setPrice(schoolCourse.getPrice());
		courseDao.save(course);
	}

	
}
