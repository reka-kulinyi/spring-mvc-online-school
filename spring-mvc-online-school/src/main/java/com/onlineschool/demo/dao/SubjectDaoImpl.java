package com.onlineschool.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlineschool.demo.entity.Subject;

@Repository
public class SubjectDaoImpl implements SubjectDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Subject getSubjectByName(String subjectName) {
		if(subjectName == null || subjectName.trim().length() == 0) {
			return null;
		}
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Subject> query = currentSession.createQuery("from Subject "
				+ "where name:=subjectName", Subject.class);
		query.setParameter("subjectName", subjectName);
		Subject subject = null;
		
		try {
			subject = query.getSingleResult();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return subject;
	}

	@Override
	public List<Subject> getAllSubjects() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Subject> query = currentSession.createQuery("from Subject order by name", 
				Subject.class);
		List<Subject> subjects = null;
		
		try {
			subjects = query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return subjects;
	}
	
	

}
