package com.codecorecix.gatherly.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(); // Bean para encriptar contraseñas
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(auth -> auth
        .anyRequest().permitAll() // Permite acceso a todos los endpoints
      )
      .csrf(csrf -> csrf.disable()) // Deshabilita CSRF
      .httpBasic(Customizer.withDefaults()) // Configuración básica personalizada
      .formLogin(form -> form.disable()); // Deshabilita el formulario de login

    return http.build();
  }
}


