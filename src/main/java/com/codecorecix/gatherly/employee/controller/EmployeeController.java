package com.codecorecix.gatherly.employee.controller;

import com.codecorecix.gatherly.employee.api.dto.request.employee.EmployeeLoginRequestDto;
import com.codecorecix.gatherly.employee.api.dto.request.employee.EmployeeRequestDto;
import com.codecorecix.gatherly.employee.api.dto.response.employee.EmployeeResponseDto;
import com.codecorecix.gatherly.employee.service.EmployeeService;
import com.codecorecix.gatherly.management.utils.GenericResponse;
import jakarta.validation.Valid;
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

  @PostMapping
  public ResponseEntity<GenericResponse<EmployeeResponseDto>> register(@RequestBody EmployeeRequestDto employeeRequestDto) {
    GenericResponse<EmployeeResponseDto> response = employeeService.register(employeeRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping("/login")
  public ResponseEntity<GenericResponse<EmployeeResponseDto>> login(@Valid @RequestBody EmployeeLoginRequestDto request) {
    return ResponseEntity.ok(employeeService.login(request.getEmail(), request.getPassword()));
  }

  @GetMapping
  public ResponseEntity<GenericResponse<List<EmployeeResponseDto>>> getAllEmployees() {
    return ResponseEntity.ok(employeeService.getAllEmployees());
  }

  @PutMapping("/{id}")
  public ResponseEntity<GenericResponse<EmployeeResponseDto>> updateEmployee(
    @PathVariable Integer id, @RequestBody EmployeeRequestDto employeeRequestDto) {
    return ResponseEntity.ok(employeeService.updateEmployee(id, employeeRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<GenericResponse<Void>> deleteEmployee(@PathVariable Integer id) {
    return ResponseEntity.ok(employeeService.deleteEmployee(id));
  }
}
