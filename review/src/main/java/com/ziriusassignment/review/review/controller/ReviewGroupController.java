package com.ziriusassignment.review.review.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ziriusassignment.review.review.config.SwaggerConfig;
import com.ziriusassignment.review.review.dto.ReviewDto;
import com.ziriusassignment.review.review.dto.ReviewGroupDto;
import com.ziriusassignment.review.review.dto.request.ReviewRequest;
import com.ziriusassignment.review.review.dto.response.ReviewResponse;
import com.ziriusassignment.review.review.service.ReviewGroupService;
import com.ziriusassignment.review.review.service.ReviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/reviewgroups")
@Api(tags = { SwaggerConfig.REVIEW_GROUPS_TAG })
public class ReviewGroupController {

  @Autowired
  ReviewGroupService reviewGroupService;

  @Autowired
  ReviewService reviewService;

  @PostMapping("")
  @PreAuthorize("hasRole('ROLE_PRODUCT')")
  @ApiOperation(value = "Using this API, you can add new review group.", notes = "Using this API, you can add new review group.", nickname = "addReviewGroup")
  public ResponseEntity<ReviewGroupDto> addReviewGroup(
      @ApiParam(value = "Review Group Notes", required = true) @RequestParam(name = "notes") String reviewGroupNotes) {
    return new ResponseEntity<>(reviewGroupService.addReviewGroup(reviewGroupNotes), HttpStatus.CREATED);
  }

  @GetMapping("/{reviewGroupId}")
  @ApiOperation(value = "Using this API, you can retrive the review group.", notes = "Using this API, you can retrive the review group.", nickname = "getReviewGroup")
  public ReviewGroupDto getReviewGroup(
      @ApiParam(value = "Review Group Identifier", required = true) @PathVariable Long reviewGroupId) {
    return reviewGroupService.getReviewGroup(reviewGroupId);
  }

  @GetMapping("/{reviewGroupId}/averagerating")
  @ApiOperation(value = "Using this API, you can retrive the average rating of the review group.", notes = "Using this API, you can retrive the average rating of the review group.", nickname = "getAverageRating")
  public ReviewGroupDto getAverageRating(
      @ApiParam(value = "Review Group Identifier", required = true) @PathVariable Long reviewGroupId) {
    return reviewGroupService.getReviewGroup(reviewGroupId);
  }

  @PostMapping("/{reviewGroupId}/reviews")
  @PreAuthorize("hasRole('ROLE_USER')")
  @ApiOperation(value = "Using this API, you can add reviews specific to a review group.", notes = "Using this API, you can add reviews specific to a review group.", nickname = "addReview")
  public ResponseEntity<ReviewDto> addReview(
      @ApiParam(value = "Review Group Identifier", required = true) @PathVariable Long reviewGroupId,
      @ApiParam(value = "Review Request", required = true) @Valid @RequestBody ReviewRequest reviewRequest) {
    return new ResponseEntity<>(reviewService.addReview(reviewGroupId, reviewRequest), HttpStatus.CREATED);
  }

  @GetMapping("/{reviewGroupId}/reviews")
  @ApiOperation(value = "Using this API, you can retrive the reviews specific to a review group.", notes = "Using this API, you can retrive the reviews specific to a review group.", nickname = "addReview")
  public ReviewResponse getReviews(
      @ApiParam(value = "Review Group Identifier", required = true) @PathVariable Long reviewGroupId,

      @ApiParam(value = "Page starts with 0", required = true) @RequestParam(defaultValue = "0") Integer page,
      @ApiParam(value = "Number of reviews to be retrived for each page", required = true) @RequestParam(defaultValue = "10") Integer size,
      @ApiParam(value = "Optional. Sort by. eg: `rate,asc` to sort by rate param in acending order."
          + "`rate,desc` to sort the rate param in decending order", required = false) @RequestParam(required = false, defaultValue = "") String sortBy) {

    Pageable pageable;
    if (!("").equals(sortBy)) {
      String[] sortArr = sortBy.split(",");

      String sortParam = sortArr[0];
      String sortValue = sortArr[1];

      if ("desc".equals(sortValue)) {
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
