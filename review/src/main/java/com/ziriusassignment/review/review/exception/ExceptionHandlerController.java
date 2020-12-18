package com.ziriusassignment.review.review.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(AccessDeniedException.class)
  public void handleAccessDeniedException(HttpServletResponse res) throws IOException {
    res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
  }

  @ExceptionHandler({ ReviewGroupNotFoundException.class })
  public void handleKnownException(HttpServletResponse res, CommonException ex) throws IOException {
    res.sendError(ex.getStatus().value(), ex.getMessage());
  }

  /*
   * @ExceptionHandler(Exception.class) public void
   * handleException(HttpServletResponse res, Exception ex) throws IOException {
   * res.sendError(HttpStatus.BAD_REQUEST.value(), "Something went wrong"); }
   */

}
