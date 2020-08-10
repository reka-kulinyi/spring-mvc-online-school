package com.onlineschool.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlineschool.demo.entity.Review;

@Repository
public class ReviewDaoImpl implements ReviewDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Review> getRecentReviews(int i) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Review> query = currentSession.createQuery("from Review r "
				+ "order by r.createdAt", Review.class);
		
		query.setMaxResults(i);
		
		List<Review> recentReviews = null;
		try {
			recentReviews = query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return recentReviews;
	}

	@Override
	public List<Review> getReviewsByInstructor(long id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Review> query = currentSession.createQuery("select r from Review r "
				+ "where r.course.instructor.id=:id", Review.class);
		query.setParameter("id", id);
		List<Review> reviews = null;
		
		try {
			reviews = query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return reviews;
	}
	
	

}
