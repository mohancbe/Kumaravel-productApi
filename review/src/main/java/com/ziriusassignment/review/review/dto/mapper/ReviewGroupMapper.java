package com.ziriusassignment.review.review.dto.mapper;

import com.ziriusassignment.review.review.dto.ReviewGroupDto;
import com.ziriusassignment.review.review.model.ReviewGroup;

public class ReviewGroupMapper {

  public static ReviewGroupDto toReviewGroupDto(ReviewGroup reviewGroup, Float averageRating) {
    ReviewGroupDto reviewGroupDto = new ReviewGroupDto()//
        .setId(reviewGroup.getId()).setAverageRating(averageRating)//
        .setNotes(reviewGroup.getNotes())//
        .setNotes(reviewGroup.getNotes())//
        .setCreatedDate(reviewGroup.getCreatedDate())//
        .setModifiedDate(reviewGroup.getModifiedDate());
    return reviewGroupDto;
  }
}
