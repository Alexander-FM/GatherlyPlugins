package com.codecorecix.gatherly.employee.api.dto.request.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeRequestDto implements Serializable {
  private Integer id;

  @NotBlank(message = "Username cannot be blank")
  private String username;

  @NotBlank(message = "Password cannot be blank")
  private String password;

  @NotBlank(message = "Name cannot be blank")
  private String name;

  @NotBlank(message = "Lastname cannot be blank")
  private String lastname;

  @Email(message = "Invalid email format")
  @NotBlank(message = "Email cannot be blank")
  private String email;

  @NotBlank(message = "Phone number is required")
  private String phone;

  @NotBlank(message = "Role cannot be blank")
  private String role;

  private String creationDate;
  private String dateCessation;
}
