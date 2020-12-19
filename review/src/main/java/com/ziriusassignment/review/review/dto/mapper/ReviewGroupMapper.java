package com.ziriusassignment.review.review.dto.mapper;

import com.ziriusassignment.review.review.dto.ReviewGroupDto;
import com.ziriusassignment.review.review.model.ReviewGroup;

public class ReviewGroupMapper {

  private ReviewGroupMapper() {
    // private constructor
  }

  public static ReviewGroupDto toReviewGroupDto(ReviewGroup reviewGroup, Float averageRating) {
    return new ReviewGroupDto()//
        .setId(reviewGroup.getId()).setAverageRating(averageRating)//
        .setNotes(reviewGroup.getNotes())//
        .setNotes(reviewGroup.getNotes())//
        .setCreatedDate(reviewGroup.getCreatedDate())//
        .setModifiedDate(reviewGroup.getModifiedDate());
  }
}
