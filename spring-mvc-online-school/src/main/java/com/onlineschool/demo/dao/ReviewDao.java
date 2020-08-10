package com.onlineschool.demo.dao;

import java.util.List;

import com.onlineschool.demo.entity.Review;

public interface ReviewDao {

	List<Review> getRecentReviews(int i);

}
