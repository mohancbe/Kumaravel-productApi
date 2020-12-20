package com.ziriusassignment.review.review.exception;

import org.springframework.http.HttpStatus;

import lombok.Setter;

@Setter
public class InvalidCredentialsException extends CommonException {

  private static final long serialVersionUID = -8917447673189582145L;

  public InvalidCredentialsException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }

}
