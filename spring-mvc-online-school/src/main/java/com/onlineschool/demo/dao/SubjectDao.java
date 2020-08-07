package com.onlineschool.demo.dao;

import java.util.List;

import com.onlineschool.demo.entity.Subject;

public interface SubjectDao {
	
	Subject getSubjectByName(String subjectName);

	List<Subject> getAllSubjects();
}
