package com.munstaff.response.error;

public class NotFoundException extends RuntimeException {

  public NotFoundException(String message, Long id) {
    super(message + ": " + id);
  };
};
