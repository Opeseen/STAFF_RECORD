package com.munstaff.exceptionHandling;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.munstaff.response.error.ErrorResponse;
import com.munstaff.response.error.NotFoundException;
import com.munstaff.response.error.ResourceAlreadyExist;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ NotFoundException.class })
  public ResponseEntity<Object> handleEntityNotFoundException(RuntimeException ex, WebRequest request) {
    ErrorResponse errorDetails = new ErrorResponse(true, ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  };

  @ExceptionHandler({ ResourceAlreadyExist.class })
  public ResponseEntity<Object> handleEntityExistException(RuntimeException ex, WebRequest request) {
    ErrorResponse errorDetails = new ErrorResponse(true, ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  };

  @ExceptionHandler({DataIntegrityViolationException.class, IllegalStateException.class})
  public ResponseEntity<Object> handleDataIntegrityException(RuntimeException ex, WebRequest request) {
    ErrorResponse errorDetails = new ErrorResponse(true, ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  };

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
    ErrorResponse errorDetails = new ErrorResponse(true, ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  };

}
