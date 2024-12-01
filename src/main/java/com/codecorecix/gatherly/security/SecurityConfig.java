package com.codecorecix.gatherly.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

  @Configuration
  public static class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**") // Permitir CORS en todas las rutas
        .allowedOriginPatterns("*") // Permitir los orígenes específicos
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // Métodos permitidos
        .allowedHeaders("*") // Permitir todos los encabezados
        .allowCredentials(true); // Permitir envío de credenciales como cookies o encabezados de autenticación
    }
  }
}