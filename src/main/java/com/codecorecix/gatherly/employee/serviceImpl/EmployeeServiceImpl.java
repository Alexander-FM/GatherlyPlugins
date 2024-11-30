package com.codecorecix.gatherly.employee.serviceImpl;

import com.codecorecix.gatherly.employee.api.dto.request.employee.EmployeeRequestDto;
import com.codecorecix.gatherly.employee.api.dto.response.employee.EmployeeResponseDto;
import com.codecorecix.gatherly.employee.mapper.EmployeeFieldsMapper;
import com.codecorecix.gatherly.employee.repository.EmployeeRepository;
import com.codecorecix.gatherly.employee.service.EmployeeService;
import com.codecorecix.gatherly.entities.Employee;
import com.codecorecix.gatherly.exceptions.GatherlyExceptions;
import com.codecorecix.gatherly.management.utils.GenericResponse;
import com.codecorecix.gatherly.management.utils.GenericResponseConstants;
import com.codecorecix.gatherly.utils.GatherlyErrorMessage;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final EmployeeFieldsMapper employeeFieldsMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public GenericResponse<EmployeeResponseDto> register(EmployeeRequestDto employeeRequestDto) {
    if (employeeRepository.findByEmail(employeeRequestDto.getEmail()).isPresent()) {
      throw new GatherlyExceptions(GatherlyErrorMessage.EMPLOYEE_ALREADY_EXISTS);
    }

    Employee employee = employeeFieldsMapper.sourceToDestination(employeeRequestDto);
    employee.setPassword(passwordEncoder.encode(employeeRequestDto.getPassword()));
    employee.setCreationDate(LocalDateTime.now());
    Employee savedEmployee = employeeRepository.save(employee);

    EmployeeResponseDto response = employeeFieldsMapper.destinationToSource(savedEmployee);
    return new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, response);
  }

  @Override
  public GenericResponse<EmployeeResponseDto> login(String email, String password) {
    Employee employee = employeeRepository.findByEmail(email)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.EMPLOYEE_NOT_FOUND));

    if (!passwordEncoder.matches(password, employee.getPassword())) {
      throw new GatherlyExceptions(GatherlyErrorMessage.EMPLOYEE_INVALID_LOGIN);
    }

    EmployeeResponseDto response = employeeFieldsMapper.destinationToSource(employee);
    return new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, response);
  }

  @Override
  public GenericResponse<List<EmployeeResponseDto>> getAllEmployees() {
    List<EmployeeResponseDto> employees = employeeRepository.findAll().stream()
      .map(employeeFieldsMapper::destinationToSource)
      .collect(Collectors.toList());

    return new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, employees);
  }

  @Override
  public GenericResponse<EmployeeResponseDto> updateEmployee(Integer id, EmployeeRequestDto employeeRequestDto) {
    Employee employee = employeeRepository.findById(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.EMPLOYEE_NOT_FOUND));

    employee.setUsername(employeeRequestDto.getUsername());
    employee.setName(employeeRequestDto.getName());
    employee.setLastname(employeeRequestDto.getLastname());
    employee.setEmail(employeeRequestDto.getEmail());
    employee.setPhone(employeeRequestDto.getPhone());
    employee.setRole(employeeRequestDto.getRole());

    Employee updatedEmployee = employeeRepository.save(employee);
    EmployeeResponseDto response = employeeFieldsMapper.destinationToSource(updatedEmployee);

    return new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, response);
  }

  @Override
  public GenericResponse<Void> deleteEmployee(Integer id) {
    Employee employee = employeeRepository.findById(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.EMPLOYEE_NOT_FOUND));

    employeeRepository.delete(employee);
    return new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, null);
  }
}
