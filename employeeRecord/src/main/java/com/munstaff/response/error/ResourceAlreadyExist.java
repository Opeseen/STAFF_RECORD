package com.munstaff.response.error;

public class ResourceAlreadyExist extends RuntimeException {

  public ResourceAlreadyExist(String message, String identity) {
    super(message + ": " + identity );
  };
};
