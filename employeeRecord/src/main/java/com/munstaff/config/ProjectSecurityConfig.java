package com.munstaff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {
  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
    http
      .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
      .csrf(csrfConfig -> csrfConfig.disable())
      .authorizeHttpRequests((requests) -> requests
        .requestMatchers("/api/employee/**").authenticated()
        .requestMatchers("/api/paygroup/**").authenticated()
        .requestMatchers("/h2/**", "/error/**").permitAll()
    );
    http.formLogin(withDefaults());
    http.httpBasic(withDefaults());
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

}
