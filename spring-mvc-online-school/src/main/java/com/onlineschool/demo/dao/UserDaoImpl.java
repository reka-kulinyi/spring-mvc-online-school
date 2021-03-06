package com.onlineschool.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
		Query<User> query = currentSession.createQuery("from User u "
				+ "where u.isTeacher=true "
				+ "order by lastName", User.class);
		List<User> instructors = null;
		try{
			instructors = query.getResultList();
		} catch(PersistenceException ex) {
			ex.printStackTrace();
		}
		return instructors;
 	}

	@Override
	public User getInstructorById(long id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> query = currentSession.createQuery("from User u "
				+ "where u.id=:id", User.class);
		
		query.setParameter("id", id);
		User user = null;
		try {
			user = query.getSingleResult();
		} catch(PersistenceException ex) {
			ex.printStackTrace();
		}
		return user;
	}
	
	@Override
	public User getUserById(long userId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> query = 
				currentSession.createQuery("from User u where u.id=:id", User.class);
		query.setParameter("id", userId);
		
		User user = null;
		try {
			user = query.getSingleResult();
		} catch(PersistenceException ex) {
			ex.printStackTrace();
		}
			
		return user;
	}

	@Override
	public List<User> getInstructorsBySubjectAndPrice(String searchSubject, BigDecimal maxPrice) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> query = currentSession.createQuery("select c.instructor from Course c "
				+ "where c.subject.name=:searchSubject "
				+ "and c.price < :maxPrice", User.class);
		query.setParameter("searchSubject", searchSubject);
		query.setParameter("maxPrice", maxPrice);
		
		List<User> instructors = null;
		try {
			instructors = query.getResultList();
		} catch(PersistenceException ex) {
			ex.printStackTrace();
		}
		return instructors;
	}

	@Override
	public List<User> getNewestInstructors(int n) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> query = currentSession.createQuery("from User u "
				+ "where u.isTeacher=true "
				+ "order by createdAt desc", User.class);
		query.setMaxResults(n);
		
		List<User> firstNInstructors = null;
		
		try {
			firstNInstructors = query.getResultList();
		} catch(PersistenceException ex) {
			ex.printStackTrace();
		}
		return firstNInstructors;
	}

	@Override
	public List<User> getInstructorsBySubject(String subject) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("select c.instructor from Course c where "
				+ "c.subject.name=:subject", User.class);
		query.setParameter("subject", subject);
		
		List<User> instructors = null;
		
		try {
			instructors = query.getResultList();
		}catch(PersistenceException ex) {
			ex.printStackTrace();
		}
		return instructors;
	}

	@Override
	public User findUserByUsername(String username) {
		
		if(username == null || username.trim().length() == 0) {
			return null;
		}
		
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("from User u "
				+ "where u.username=:username", User.class);
		query.setParameter("username", username);
		
		User user = null;
		
		try {
			user = query.getSingleResult();
		} catch(PersistenceException ex) {
			ex.printStackTrace();
		}
		
		return user;
	}

	@Override
	public void save(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}
}
