package com.ziriusassignment.review.review.exception;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ziriusassignment.review.review.dto.ErrorDetails;

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

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

    StringBuilder errorMessage = new StringBuilder("");

    for (ObjectError error : allErrors) {
      errorMessage.append(error.getDefaultMessage()).append(";");
    }
    ErrorDetails error = new ErrorDetails(new Date(), "Bad Request", errorMessage.toString());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public void handleException(HttpServletResponse res, Exception ex) throws IOException {
    res.sendError(HttpStatus.BAD_REQUEST.value(), "Something went wrong");
  }

}
