package com.codecorecix.gatherly.employee.controller;

import com.codecorecix.gatherly.employee.dto.request.employee.EmployeeLoginRequestDto;
import com.codecorecix.gatherly.employee.dto.request.employee.EmployeeRequestDto;
import com.codecorecix.gatherly.employee.dto.response.employee.EmployeeResponseDto;
import com.codecorecix.gatherly.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/employee")
@RequiredArgsConstructor
public class EmployeeController {
  private final EmployeeService employeeService;

  // Endpoint para registrar un empleado
  @PostMapping("/register")
  public ResponseEntity<EmployeeResponseDto> register(@RequestBody EmployeeRequestDto employeeRequestDto) {
    EmployeeResponseDto responseDto = employeeService.register(employeeRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  // Endpoint para el login
  @PostMapping("/login")
  public ResponseEntity<EmployeeResponseDto> login(@RequestBody EmployeeLoginRequestDto loginRequestDto) {
    EmployeeResponseDto responseDto = employeeService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    return ResponseEntity.ok(responseDto);
  }
}