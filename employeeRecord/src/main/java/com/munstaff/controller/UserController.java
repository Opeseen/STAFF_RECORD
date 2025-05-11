package com.munstaff.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.munstaff.dto.UserDTO;
import com.munstaff.repository.UserRepository;
import com.munstaff.response.Constant.ConstantResponse;
import com.munstaff.response.success.SuccessResponse;
import com.munstaff.services.UserService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

  UserService userService;
  UserRepository userRepository;
  @PostMapping("/register")
  public ResponseEntity<?> createUser(@RequestBody UserDTO entity) {
    UserDTO newUser = userService.saveUser(entity);
      SuccessResponse successDetails = new SuccessResponse(true, ConstantResponse.SingleRecordResponse,
        ConstantResponse.CreateUserResponse,
        newUser);
    return new ResponseEntity<>(successDetails, HttpStatus.CREATED);
  }

}
