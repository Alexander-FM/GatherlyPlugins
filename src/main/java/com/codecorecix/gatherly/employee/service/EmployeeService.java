package com.codecorecix.gatherly.employee.service;

import com.codecorecix.gatherly.employee.api.dto.request.employee.EmployeeRequestDto;
import com.codecorecix.gatherly.employee.api.dto.response.employee.EmployeeResponseDto;

public interface EmployeeService {

  EmployeeResponseDto register(EmployeeRequestDto employeeRequestDto);

  // Login de empleado
  EmployeeResponseDto login(String username, String password);
}
