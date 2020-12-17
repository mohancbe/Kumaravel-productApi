package com.ziriusassignment.review.review.service;

import com.ziriusassignment.review.review.dto.ReviewGroupDto;

public interface ReviewGroupService {

  ReviewGroupDto addReviewGroup();

  ReviewGroupDto getReviewGroup(Long reviewGroupId);

}
