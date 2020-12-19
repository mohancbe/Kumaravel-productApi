package com.ziriusassignment.review.review.dto.response;

import java.util.List;

import com.ziriusassignment.review.review.dto.ReviewDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponse {
  private Integer page;
  private Integer size;
  private Long totalRecords;
  private Integer totalPages;
  private List<ReviewDto> reviews;
}
