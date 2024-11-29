package com.codecorecix.gatherly.employee.service;

import com.codecorecix.gatherly.employee.api.dto.request.employee.EmployeeRequestDto;
import com.codecorecix.gatherly.employee.api.dto.response.employee.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

  EmployeeResponseDto register(EmployeeRequestDto employeeRequestDto);

  // Login de empleado
  EmployeeResponseDto login(String username, String password);

  List<EmployeeResponseDto> getAllEmployees();

  EmployeeResponseDto updateEmployee(Integer id, EmployeeRequestDto employeeRequestDto);

  void deleteEmployee(Integer id);
}
