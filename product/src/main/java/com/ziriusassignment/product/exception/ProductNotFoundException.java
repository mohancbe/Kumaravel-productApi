package com.ziriusassignment.product.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends CommonException {

  private static final long serialVersionUID = -9050560719936060184L;

  public ProductNotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }

}
