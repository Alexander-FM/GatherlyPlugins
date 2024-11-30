package com.codecorecix.gatherly.employee.api.dto.request.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeLoginRequestDto {

  @Email(message = "Invalid email format")
  @NotBlank(message = "Email cannot be blank")
  private String email;

  @NotBlank(message = "Password cannot be blank")
  private String password;
}
