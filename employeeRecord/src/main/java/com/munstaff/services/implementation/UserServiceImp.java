package com.munstaff.services.implementation;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.munstaff.dto.UserDTO;
import com.munstaff.entity.Users;
import com.munstaff.mapper.UserMapper;
import com.munstaff.repository.UserRepository;
import com.munstaff.response.error.NotFoundException;
import com.munstaff.response.error.ResourceAlreadyExist;
import com.munstaff.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDTO saveUser(UserDTO userDTO) {
    // Check if user already exist
    if(userRepository.existsByEmail(userDTO.getEmail().toLowerCase().trim())){
      throw new ResourceAlreadyExist("A user already exist in our record with the email", userDTO.getEmail());
    }
    userDTO.setEmail(userDTO.getEmail().toLowerCase().trim());
    userDTO.setPwd(passwordEncoder.encode(userDTO.getPwd().trim()));
    Users user = userMapper.convertToEntity(userDTO);
    Users savedUser = userRepository.save(user);
    savedUser.setPwd(null);
    return userMapper.convertToDto(savedUser);
  }

  @Override
  public UserDTO getByEmail(String email){
    Optional<Users> user = userRepository.findByEmail(email);
    if(!user.isPresent()){
      throw new RuntimeException("No user found with the email specified");
    }
    return userMapper.convertToDto(user.get());
  }

}
