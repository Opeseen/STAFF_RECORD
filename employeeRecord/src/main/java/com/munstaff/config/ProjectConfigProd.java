package com.munstaff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.munstaff.exceptionHandling.CustomAccessDeniedHandler;
import com.munstaff.exceptionHandling.CustomBasicAuthEntryPoint;

import org.modelmapper.ModelMapper;

import static org.springframework.security.config.Customizer.withDefaults;
@Profile("prod")
@Configuration
public class ProjectConfigProd {
  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
    http
      .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests((requests) -> requests
        .requestMatchers("/api/employee/**").authenticated()
        .requestMatchers("/api/paygroup/**").authenticated()
        .requestMatchers("/h2/**", "/error/**", "/api/register").permitAll()
    );
    http.formLogin(withDefaults());
    http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthEntryPoint()));
    http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public ModelMapper modelMapper(){
    return new ModelMapper();
  }

}
