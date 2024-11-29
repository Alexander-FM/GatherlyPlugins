package com.codecorecix.gatherly.employee.mapper;

import com.codecorecix.gatherly.employee.dto.request.employee.EmployeeRequestDto;
import com.codecorecix.gatherly.employee.dto.response.employee.EmployeeResponseDto;
import com.codecorecix.gatherly.entities.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeFieldsMapper {

  Employee sourceToDestination(final EmployeeRequestDto employeeRequestDto);

  EmployeeResponseDto destinationToSource(final Employee employee);

  List<EmployeeResponseDto> sourceToDestination(final List<Employee> employees);
}