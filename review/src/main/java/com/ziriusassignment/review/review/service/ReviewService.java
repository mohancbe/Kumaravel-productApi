package com.ziriusassignment.review.review.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ziriusassignment.review.review.dto.ReviewDto;
import com.ziriusassignment.review.review.dto.request.ReviewRequest;

public interface ReviewService {

  ReviewDto addReview(Long reviewGroupId, ReviewRequest reviewRequest);

  List<ReviewDto> getReviews(Long reviewGroupId, Pageable pageable);

}
