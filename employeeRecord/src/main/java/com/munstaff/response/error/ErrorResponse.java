package com.munstaff.response.error;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Getter
@Setter
public class ErrorResponse {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private Boolean error;
  private Object message;
  private Object path;
  private LocalDateTime timestamp;

  public ErrorResponse(Boolean error, Object message, Object path) {
    super();
    this.error = error;
    this.message = message;
    this.path = path;
    this.timestamp = LocalDateTime.now();
  };
}
