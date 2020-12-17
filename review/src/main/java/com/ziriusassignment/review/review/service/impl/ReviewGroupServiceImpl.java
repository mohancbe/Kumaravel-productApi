package com.ziriusassignment.review.review.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziriusassignment.review.review.dto.ReviewGroupDto;
import com.ziriusassignment.review.review.dto.mapper.ReviewGroupMapper;
import com.ziriusassignment.review.review.model.ReviewGroup;
import com.ziriusassignment.review.review.repository.ReviewGroupRepository;
import com.ziriusassignment.review.review.service.ReviewGroupService;

@Service
public class ReviewGroupServiceImpl implements ReviewGroupService {

  @Autowired
  private ReviewGroupRepository reviewGroupRepository;
  
  @Override
  public ReviewGroupDto addReviewGroup() {
    ReviewGroup reviewGroup = reviewGroupRepository.save(new ReviewGroup());
    return ReviewGroupMapper.toReviewGroupDto(reviewGroup, 0F);
  }

  @Override
  public ReviewGroupDto getReviewGroup(Long reviewGroupId) {
    Optional<ReviewGroup> reviewGroup = reviewGroupRepository.findById(reviewGroupId);
    if (reviewGroup.isEmpty()) {
      throw new RuntimeException();
    }
    Float averageRating = reviewGroupRepository.getAverageRating(reviewGroupId);
    return ReviewGroupMapper.toReviewGroupDto(reviewGroup.get(), averageRating);
  }

}
