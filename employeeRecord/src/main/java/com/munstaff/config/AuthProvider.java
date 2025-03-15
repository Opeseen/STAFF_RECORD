package com.munstaff.config;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@Profile("!prod")
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider{
  
  private final UserDetailsService userDetailsService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException{
    String email = authentication.getName();
    String password = authentication.getCredentials().toString();
    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
    return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication){
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
  }
}
