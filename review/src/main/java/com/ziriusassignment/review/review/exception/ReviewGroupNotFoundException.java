package com.ziriusassignment.review.review.exception;

import org.springframework.http.HttpStatus;

public class ReviewGroupNotFoundException extends CommonException {

  private static final long serialVersionUID = -9050560719936060184L;

  public ReviewGroupNotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }

}
