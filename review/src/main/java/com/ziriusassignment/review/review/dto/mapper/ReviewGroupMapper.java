package com.ziriusassignment.review.review.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ziriusassignment.review.review.dto.ReviewDto;
import com.ziriusassignment.review.review.dto.ReviewGroupDto;
import com.ziriusassignment.review.review.model.ReviewGroup;

public class ReviewGroupMapper {

  public static ReviewGroupDto toReviewGroupDto(ReviewGroup reviewGroup, Float averageRating) {
    List<ReviewDto> reviewDto;
    ReviewGroupDto reviewGroupDto = new ReviewGroupDto().setId(reviewGroup.getId())
      .setAverageRating(averageRating)
      .setNotes(reviewGroup.getNotes());
    
    if(reviewGroup.getReviews() != null) {
      reviewDto = new ArrayList<ReviewDto>(
          reviewGroup.getReviews()
          .stream()
          .map(review ->
            new ReviewDto()
            .setComment(review.getComment())
            .setId(review.getId())
            .setRate(review.getRate()))
            .collect(Collectors.toList()));
    } else {
      reviewDto = new ArrayList<>();
    }
    reviewGroupDto.setReviews(reviewDto);
    return reviewGroupDto;
  }
}
