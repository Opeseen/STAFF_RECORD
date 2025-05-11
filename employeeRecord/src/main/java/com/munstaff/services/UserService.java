package com.munstaff.services;

import com.munstaff.dto.UserDTO;

public interface UserService {
  UserDTO saveUser(UserDTO userDTO);
  UserDTO getByEmail(String email);
}
