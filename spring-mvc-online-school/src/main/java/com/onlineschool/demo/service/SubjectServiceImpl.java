package com.onlineschool.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineschool.demo.dao.SubjectDao;
import com.onlineschool.demo.entity.Subject;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectDao subjectDao;
	
	@Override
	@Transactional
	public List<Subject> getAllSubjects() {
		return subjectDao.getAllSubjects();
	}

}
