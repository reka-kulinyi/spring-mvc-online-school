package com.onlineschool.demo.service;

import com.onlineschool.demo.dao.ReviewDao;
import com.onlineschool.demo.entity.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewDao reviewDao;

    @InjectMocks
    ReviewService reviewService;

    @Test
    void getRecentReviews() {
        // given
        List<Review> recentReviews = new ArrayList<>();
        recentReviews.add(new Review());
        recentReviews.add(new Review());
        when(reviewDao.getRecentReviews(anyInt())).thenReturn(recentReviews);

        // when
        List<Review> returnedReviews = reviewService.getRecentReviews(2);

        //then
        then(reviewDao).should().getRecentReviews(2);
        assertThat(returnedReviews).hasSize(2);
    }

    @Test
    void getReviewsByInstructor() {

        // given
        List<Review> reviewsByInstructor = new ArrayList<>();
        reviewsByInstructor.add(new Review());
        reviewsByInstructor.add(new Review());
        when(reviewDao.getReviewsByInstructor(anyLong())).thenReturn(reviewsByInstructor);

        // when
        List<Review> returnedReviews = reviewService.getReviewsByInstructor(1L);

        //then
        then(reviewDao).should().getReviewsByInstructor(1L);
        assertThat(returnedReviews).hasSize(2);
    }
}