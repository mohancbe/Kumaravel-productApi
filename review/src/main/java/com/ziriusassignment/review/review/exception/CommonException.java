package com.ziriusassignment.review.review.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommonException extends RuntimeException {
  private static final long serialVersionUID = -1539409354436223484L;
  private final String message;
  private final HttpStatus status;
}
