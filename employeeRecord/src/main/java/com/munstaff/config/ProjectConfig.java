package com.munstaff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import com.munstaff.exceptionHandling.CustomAccessDeniedHandler;
import com.munstaff.exceptionHandling.CustomBasicAuthEntryPoint;
import com.munstaff.filter.CsrfCookieFilter;

import org.modelmapper.ModelMapper;

import static org.springframework.security.config.Customizer.withDefaults;

@Profile("!prod")
@Configuration
public class ProjectConfig {
  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
    CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
    http
      .securityContext(contextConfig -> contextConfig.requireExplicitSave(false))
      .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
      .cors(cors -> cors.configurationSource(new CorsConfig()))
      .csrf(csrfConfig -> csrfConfig.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
        .ignoringRequestMatchers("/h2/**", "/api/register")
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
      .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
      .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure()) // Only HTTP
      .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
      .authorizeHttpRequests((requests) -> requests
        .requestMatchers("/api/employee/**").authenticated()
        .requestMatchers("/api/paygroup/**").authenticated()
        .requestMatchers("/h2/**", "/error/**", "/api/register", "/invalidSession").permitAll()
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
