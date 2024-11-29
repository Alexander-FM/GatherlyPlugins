package com.codecorecix.gatherly.employee.dto.request.employee;

import lombok.Data;

@Data
public class EmployeeLoginRequestDto {
  private String email;
  private String password;
}
