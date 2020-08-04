package com.onlineschool.demo.dao;

import java.util.List;

import javax.persistence.Query;

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
		Query query = session.createQuery("from Course where instructor=:instructor");
		query.setParameter("instructor", instructor);
		List<Course> courses = null;
		try {
			courses = query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return courses;
	}

}
