package com.ziriusassignment.review.review.service;

import org.springframework.data.domain.Pageable;

import com.ziriusassignment.review.review.dto.ReviewDto;
import com.ziriusassignment.review.review.dto.request.ReviewRequest;
import com.ziriusassignment.review.review.dto.response.ReviewResponse;

public interface ReviewService {

  ReviewDto addReview(Long reviewGroupId, ReviewRequest reviewRequest);

  ReviewResponse getReviews(Long reviewGroupId, Pageable pageable);

}
