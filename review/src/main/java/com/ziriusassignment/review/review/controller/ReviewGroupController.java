package com.ziriusassignment.review.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ziriusassignment.review.review.dto.ReviewDto;
import com.ziriusassignment.review.review.dto.ReviewGroupDto;
import com.ziriusassignment.review.review.dto.request.ReviewRequest;
import com.ziriusassignment.review.review.service.ReviewGroupService;
import com.ziriusassignment.review.review.service.ReviewService;

@RestController
@RequestMapping("/reviewgroups")
public class ReviewGroupController {
	
  @Autowired
  ReviewGroupService reviewGroupService;
  
  @Autowired
  ReviewService reviewService;
  
	@PostMapping("")
	public ReviewGroupDto addReviewGroup() {
		return reviewGroupService.addReviewGroup();
	}
	
	@GetMapping("/{reviewGroupId}")
	public ReviewGroupDto getReviewGroup(@PathVariable Long reviewGroupId) {
		return reviewGroupService.getReviewGroup(reviewGroupId);
	}
	
	@GetMapping("/{reviewGroupId}/averagerating")
  public ReviewGroupDto getAverageRating(@PathVariable Long reviewGroupId) {
	  return reviewGroupService.getReviewGroup(reviewGroupId);
	}
	
  @PostMapping("/{reviewGroupId}/reviews")
  public ReviewDto addReview(@PathVariable Long reviewGroupId
      , @RequestBody ReviewRequest reviewRequest) {
    return reviewService.addReview(reviewGroupId, reviewRequest);
  }

  @GetMapping("/{reviewGroupId}/reviews")
  public List<ReviewDto> getReviews(@PathVariable Long reviewGroupId
      , @RequestParam Integer page
      , @RequestParam Integer size
      , @RequestParam(defaultValue = "") String sortBy) {
    
    Pageable pageable;
    if(!sortBy.equals("")) {
      String[] sortArr = sortBy.split(",");
      
      String sortParam = sortArr[0];
      String sortValue = sortArr[1];
      
      if("desc".equals(sortValue)) {
        pageable = PageRequest.of(page, size, Sort.by(sortParam).descending());
      } else {
        pageable = PageRequest.of(page, size, Sort.by(sortParam).ascending());
      }
    } else {
      pageable = PageRequest.of(page, size);
    }
    return reviewService.getReviews(reviewGroupId, pageable);
  }

}
