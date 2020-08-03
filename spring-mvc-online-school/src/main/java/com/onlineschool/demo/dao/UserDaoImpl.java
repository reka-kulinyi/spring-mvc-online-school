package com.onlineschool.demo.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlineschool.demo.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> getAllInstructors() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("from User u "
				+ "where u.isTeacher=true "
				+ "order by lastName", User.class);
		List<User> instructors = query.getResultList();
		return instructors;
 	}

}
