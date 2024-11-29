package com.codecorecix.gatherly.employee.serviceImpl;

import com.codecorecix.gatherly.employee.dto.request.employee.EmployeeRequestDto;
import com.codecorecix.gatherly.employee.dto.response.employee.EmployeeResponseDto;
import com.codecorecix.gatherly.employee.mapper.EmployeeFieldsMapper;
import com.codecorecix.gatherly.employee.repository.EmployeeRepository;
import com.codecorecix.gatherly.employee.service.EmployeeService;
import com.codecorecix.gatherly.entities.Employee;
import com.codecorecix.gatherly.exceptions.GatherlyExceptions;
import com.codecorecix.gatherly.utils.GatherlyErrorMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  private final EmployeeFieldsMapper employeeFieldsMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public EmployeeResponseDto register(EmployeeRequestDto employeeRequestDto) {
    if (employeeRepository.findByEmail(employeeRequestDto.getEmail()).isPresent()) {
      throw new GatherlyExceptions(GatherlyErrorMessage.ERROR_REGISTER);
    }

    Employee employee = employeeFieldsMapper.sourceToDestination(employeeRequestDto);
    employee.setPassword(passwordEncoder.encode(employeeRequestDto.getPassword()));
    employee.setCreationDate(LocalDateTime.now());
    Employee savedEmployee = employeeRepository.save(employee);

    return employeeFieldsMapper.destinationToSource(savedEmployee);
  }

  @Override
  public EmployeeResponseDto login(String email, String password) {
    Employee employee = employeeRepository.findByEmail(email)
      .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con el email: " + email));

    if (!passwordEncoder.matches(password, employee.getPassword())) {
      throw new GatherlyExceptions(GatherlyErrorMessage.ERROR_LOGIN);
    }

    return employeeFieldsMapper.destinationToSource(employee);
  }

}
