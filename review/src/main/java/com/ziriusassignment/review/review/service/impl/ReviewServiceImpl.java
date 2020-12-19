package com.ziriusassignment.review.review.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ziriusassignment.review.review.dto.ReviewDto;
import com.ziriusassignment.review.review.dto.mapper.ReviewMapper;
import com.ziriusassignment.review.review.dto.request.ReviewRequest;
import com.ziriusassignment.review.review.dto.response.ReviewResponse;
import com.ziriusassignment.review.review.exception.ReviewGroupNotFoundException;
import com.ziriusassignment.review.review.model.Review;
import com.ziriusassignment.review.review.model.ReviewGroup;
import com.ziriusassignment.review.review.repository.ReviewGroupRepository;
import com.ziriusassignment.review.review.repository.ReviewRepository;
import com.ziriusassignment.review.review.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private ReviewGroupRepository reviewGroupRepository;

  @Override
  public ReviewDto addReview(Long reviewGroupId, ReviewRequest reviewRequest) {
    Optional<ReviewGroup> reviewGroup = reviewGroupRepository.findById(reviewGroupId);
    if (reviewGroup.isEmpty()) {
      throw new ReviewGroupNotFoundException("Review group id: " + reviewGroupId + " is not found");
    }

    Review review = new Review();
    review.setComment(reviewRequest.getComment());
    review.setRate(reviewRequest.getRate());
    review.setReviewGroup(reviewGroup.get());
    return ReviewMapper.toReviewDto(reviewRepository.save(review));
  }

  @Override
  public ReviewResponse getReviews(Long reviewGroupId, Pageable pageable) {

    Optional<ReviewGroup> reviewGroup = reviewGroupRepository.findById(reviewGroupId);
    if (reviewGroup.isEmpty()) {
      throw new ReviewGroupNotFoundException("Review group id: " + reviewGroupId + " is not found");
    }

    Page<Review> reviews = reviewRepository.findByReviewGroupId(reviewGroupId, pageable);
    
    ReviewResponse reviewResponse = new ReviewResponse();
    reviewResponse.setPage(pageable.getPageNumber());
    reviewResponse.setSize(pageable.getPageSize());
    reviewResponse.setTotalPages(reviews.getTotalPages());
    reviewResponse.setTotalRecords(reviews.getTotalElements());
    reviewResponse.setReviews(
        reviews.get()
        .map(review -> new ReviewDto().setId(review.getId()).setRate(review.getRate()).setComment(review.getComment()))
        .collect(Collectors.toList())
    );
    
    return reviewResponse;
  }

}
