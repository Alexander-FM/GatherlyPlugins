package com.codecorecix.gatherly.employee.controller;

import com.codecorecix.gatherly.employee.api.dto.request.employee.EmployeeLoginRequestDto;
import com.codecorecix.gatherly.employee.api.dto.request.employee.EmployeeRequestDto;
import com.codecorecix.gatherly.employee.api.dto.response.employee.EmployeeResponseDto;
import com.codecorecix.gatherly.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @GetMapping
  public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
    return ResponseEntity.ok(employeeService.getAllEmployees());
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmployeeResponseDto> updateEmployee(
    @PathVariable Integer id,
    @RequestBody EmployeeRequestDto employeeRequestDto) {
    EmployeeResponseDto updatedEmployee = employeeService.updateEmployee(id, employeeRequestDto);
    return ResponseEntity.ok(updatedEmployee);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
    employeeService.deleteEmployee(id);
    return ResponseEntity.noContent().build();
  }
}