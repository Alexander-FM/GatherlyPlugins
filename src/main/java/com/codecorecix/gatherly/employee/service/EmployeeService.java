package com.codecorecix.gatherly.employee.service;

import com.codecorecix.gatherly.employee.api.dto.request.employee.EmployeeRequestDto;
import com.codecorecix.gatherly.employee.api.dto.response.employee.EmployeeResponseDto;
import com.codecorecix.gatherly.management.utils.GenericResponse;

import java.util.List;

public interface EmployeeService {

  GenericResponse<EmployeeResponseDto> register(EmployeeRequestDto employeeRequestDto);

  GenericResponse<EmployeeResponseDto> login(String email, String password);

  GenericResponse<List<EmployeeResponseDto>> getAllEmployees();

  GenericResponse<EmployeeResponseDto> updateEmployee(Integer id, EmployeeRequestDto employeeRequestDto);

  GenericResponse<Void> deleteEmployee(Integer id);
}