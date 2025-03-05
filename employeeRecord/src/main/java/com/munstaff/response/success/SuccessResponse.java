package com.munstaff.response.success;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Getter
@Setter
public class SuccessResponse {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private Boolean success;
  private Number result;
  private Object message;
  private Object details;
  private LocalDateTime timestamp;

  public SuccessResponse(Boolean success, Number result, Object message, Object details) {
    super();
    this.success = success;
    this.result = result;
    this.message = message;
    this.details = details;
    this.timestamp = LocalDateTime.now();
  };
};
