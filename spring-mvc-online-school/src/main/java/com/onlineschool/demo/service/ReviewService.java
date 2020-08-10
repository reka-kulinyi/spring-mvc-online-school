package com.onlineschool.demo.service;

import java.util.List;

import com.onlineschool.demo.entity.Review;

public interface ReviewService {

	List<Review> getRecentReviews(int i);

	List<Review> getReviewsByInstructor(long id);
	
}
