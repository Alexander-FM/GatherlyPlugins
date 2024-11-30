package com.codecorecix.gatherly.customer.serviceImpl;

import com.codecorecix.gatherly.customer.api.dto.request.customer.CustomerRequestDto;
import com.codecorecix.gatherly.customer.api.dto.response.customer.CustomerResponseDto;
import com.codecorecix.gatherly.customer.mapper.CustomerFieldsMapper;
import com.codecorecix.gatherly.customer.repository.CustomerRepository;
import com.codecorecix.gatherly.customer.service.CustomerService;
import com.codecorecix.gatherly.entities.Customer;
import com.codecorecix.gatherly.exceptions.GatherlyExceptions;
import com.codecorecix.gatherly.management.utils.GenericResponse;
import com.codecorecix.gatherly.management.utils.GenericResponseConstants;
import com.codecorecix.gatherly.utils.GatherlyErrorMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerFieldsMapper customerFieldsMapper;

  @Override
  public GenericResponse<CustomerResponseDto> registerCustomer(CustomerRequestDto customerRequestDto) {
    if (customerRepository.existsByEmail(customerRequestDto.getEmail())) {
      throw new GatherlyExceptions(GatherlyErrorMessage.CUSTOMER_ALREADY_EXISTS);
    }

    Customer customer = customerFieldsMapper.sourceToDestination(customerRequestDto);
    Customer savedCustomer = customerRepository.save(customer);
    CustomerResponseDto response = customerFieldsMapper.destinationToSource(savedCustomer);

    return new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, response);
  }

  @Override
  public GenericResponse<List<CustomerResponseDto>> getAllCustomers() {
    List<CustomerResponseDto> customers = customerRepository.findAll().stream()
      .map(customerFieldsMapper::destinationToSource)
      .collect(Collectors.toList());
    return new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, customers);
  }

  @Override
  public GenericResponse<CustomerResponseDto> updateCustomer(Integer id, CustomerRequestDto customerRequestDto) {
    Customer customer = customerRepository.findById(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.CUSTOMER_NOT_FOUND));

    customer.setName(customerRequestDto.getName());
    customer.setLastname(customerRequestDto.getLastname());
    customer.setPhone(customerRequestDto.getPhone());
    customer.setEmail(customerRequestDto.getEmail());
    customer.setAddress(customerRequestDto.getAddress());

    Customer updatedCustomer = customerRepository.save(customer);
    CustomerResponseDto response = customerFieldsMapper.destinationToSource(updatedCustomer);

    return new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, response);
  }

  @Override
  public GenericResponse<Void> deleteCustomer(Integer id) {
    Customer customer = customerRepository.findById(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.CUSTOMER_NOT_FOUND));

    customerRepository.delete(customer);
    return new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, null);
  }
}
