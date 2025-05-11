package com.munstaff.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {
  private int id;
  private String email;
  private String role;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String pwd;
}
