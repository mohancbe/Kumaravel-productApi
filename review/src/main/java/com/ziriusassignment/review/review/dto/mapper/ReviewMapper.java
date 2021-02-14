package com.ziriusassignment.review.review.dto.mapper;

import com.ziriusassignment.review.review.dto.ReviewDto;
import com.ziriusassignment.review.review.model.Review;

public class ReviewMapper {
  
  private ReviewMapper() {
    //private constructor
  }
  
  public static ReviewDto toReviewDto(Review review) {
    return new ReviewDto().setId(review.getId())
      .setId(review.getId())
      .setComment(review.getComment())
      .setRate(review.getRate())
      .setCreatedDate(review.getCreatedDate())
      .setModifiedDate(review.getModifiedDate());
  }
}
