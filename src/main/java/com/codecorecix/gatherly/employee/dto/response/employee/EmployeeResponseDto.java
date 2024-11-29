package com.codecorecix.gatherly.employee.dto.response.employee;

import lombok.Data;

@Data
public class EmployeeResponseDto {
  private Integer id;
  private String username;
  private String name;
  private String lastname;
  private String email;
  private String role;
  private String phone;
}
