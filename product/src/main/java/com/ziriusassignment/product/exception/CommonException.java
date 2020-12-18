package com.ziriusassignment.product.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommonException extends RuntimeException {
  private static final long serialVersionUID = -1539409354436223484L;
  private String message;
  private HttpStatus status;
}
