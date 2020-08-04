package com.onlineschool.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.onlineschool.demo.entity.Subject;

public class SubjectDaoImpl implements SubjectDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Subject getSubjectByName(String subjectName) {
		if(subjectName == null || subjectName.trim().length() == 0) {
			return null;
		}
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Subject> query = currentSession.createQuery("from Subject where name:=subjectName");
		query.setParameter("subjectName", subjectName);
		Subject subject = null;
		
		try {
			subject = query.getSingleResult();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return subject;
	}

}
