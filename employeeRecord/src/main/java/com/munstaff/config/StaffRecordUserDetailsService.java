package com.munstaff.config;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.munstaff.model.Users;
import com.munstaff.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StaffRecordUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Users users = userRepository.findByEmail(email).orElseThrow(() -> new 
      UsernameNotFoundException("User details not found for the email: " + email));
    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(users.getRole()));
    return new User(users.getEmail(), users.getPwd(), authorities);
  }
}
