package com.ziriusassignment.product.service.impl;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ziriusassignment.product.dto.ReviewDto;
import com.ziriusassignment.product.dto.ReviewGroupDto;
import com.ziriusassignment.product.dto.request.ReviewRequest;

@FeignClient(name="review", url="localhost:8080")
public interface ProductReviewService {

  @PostMapping("/reviewgroups")
  public ReviewGroupDto addReviewGroup(@RequestHeader("Authorization") String jwtToken);
  
  @GetMapping("/reviewgroups/{reviewGroupId}")
  public ReviewGroupDto getReviewGroup(@PathVariable Long reviewGroupId);
  
  @PostMapping("/reviewgroups/{reviewGroupId}/reviews")
  public ReviewDto addReview(@RequestHeader("Authorization") String jwtToken, @PathVariable Long reviewGroupId
      , @RequestBody ReviewRequest reviewRequest);
  
  @GetMapping("/reviewgroups/{reviewGroupId}/reviews")
  public List<ReviewDto> getReviews(@PathVariable Long reviewGroupId
      , @RequestParam Integer page
      , @RequestParam Integer size
      , @RequestParam(defaultValue = "") String sortBy);

  @GetMapping("/reviewgroups/{reviewGroupId}/averagerating")
  public ReviewGroupDto getAverageRating(@PathVariable Long reviewGroupId);

}
