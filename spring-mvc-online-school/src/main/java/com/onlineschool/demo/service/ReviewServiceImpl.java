package com.onlineschool.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineschool.demo.dao.ReviewDao;
import com.onlineschool.demo.entity.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	
	@Override
	@Transactional
	public List<Review> getRecentReviews(int i) {
		return reviewDao.getRecentReviews(i);
	}

	@Override
	@Transactional
	public List<Review> getReviewsByInstructor(long id) {
		return reviewDao.getReviewsByInstructor(id);
	}

}
