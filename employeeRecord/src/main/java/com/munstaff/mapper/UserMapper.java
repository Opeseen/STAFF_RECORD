package com.munstaff.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.munstaff.dto.UserDTO;
import com.munstaff.entity.Users;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

  private final ModelMapper modelMapper;

  // Convert Entity to DTO
  public UserDTO convertToDto(Users user) {
    UserDTO userDTO = modelMapper.map(user, UserDTO.class);
    return userDTO;
  }
  // Convert DTO to Entity
  public Users convertToEntity(UserDTO userDTO) {
    Users user = modelMapper.map(userDTO, Users.class);
    return user;
  }

}
