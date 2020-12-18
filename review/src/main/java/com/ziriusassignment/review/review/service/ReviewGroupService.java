package com.ziriusassignment.review.review.service;

import com.ziriusassignment.review.review.dto.ReviewGroupDto;

public interface ReviewGroupService {

  ReviewGroupDto addReviewGroup(String reviewGroupNotes);

  ReviewGroupDto getReviewGroup(Long reviewGroupId);

}
