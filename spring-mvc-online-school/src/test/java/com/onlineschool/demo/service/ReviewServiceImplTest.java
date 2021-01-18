package com.onlineschool.demo.service;

import com.onlineschool.demo.dao.ReviewDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewDao reviewDao;

    @InjectMocks
    CourseService courseService;

    @Test
    void getRecentReviews() {
    }

    @Test
    void getReviewsByInstructor() {
    }
}