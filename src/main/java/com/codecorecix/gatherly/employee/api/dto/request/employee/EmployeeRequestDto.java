package com.codecorecix.gatherly.employee.api.dto.request.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeRequestDto implements Serializable {
  private Integer id;

  @NotBlank(message = "El nombre de usuario es obligatorio")
  private String username;

  @NotBlank(message = "La contraseña es obligatoria")
  private String password;

  @NotBlank(message = "El nombre es obligatorio")
  private String name;

  @NotBlank(message = "El apellido es obligatorio")
  private String lastname;

  @Email(message = "El correo debe ser válido")
  private String email;

  @NotBlank(message = "El número de teléfono es obligatorio")
  private String phone;

  @NotBlank(message = "El rol es obligatorio")
  private String role;

  private String creationDate;
  private String dateCessation;
}
