package com.onlineschool.demo.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.query.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlineschool.demo.entity.Course;
import com.onlineschool.demo.entity.User;

@Repository
public class CourseDaoImpl implements CourseDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Course> getCoursesByInstructor(User instructor) {
		if(instructor == null) {
			return null;
		}
		Session session = sessionFactory.getCurrentSession();
		Query<Course> query = session.createQuery("from Course c where instructor=:instructor "
				+ "order by c.subject.name", Course.class);
		query.setParameter("instructor", instructor);
		List<Course> courses = null;
		try {
			courses = query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return courses;
	}

	@Override
	public void deleteCourseById(long courseId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("delete from Course "
				+ "where id=:courseId");
		query.setParameter("courseId", courseId);
		try {
			query.executeUpdate();
		} catch(PersistenceException | IllegalStateException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void save(Course course) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(course);
		
	}
	
}
