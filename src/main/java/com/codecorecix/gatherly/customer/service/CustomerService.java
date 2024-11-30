package com.codecorecix.gatherly.customer.service;

import com.codecorecix.gatherly.customer.api.dto.request.customer.CustomerRequestDto;
import com.codecorecix.gatherly.customer.api.dto.response.customer.CustomerResponseDto;
import com.codecorecix.gatherly.management.utils.GenericResponse;

import java.util.List;

public interface CustomerService {

  GenericResponse<CustomerResponseDto> registerCustomer(CustomerRequestDto customerRequestDto);

  GenericResponse<List<CustomerResponseDto>> getAllCustomers();

  GenericResponse<CustomerResponseDto> updateCustomer(Integer id, CustomerRequestDto customerRequestDto);

  GenericResponse<Void> deleteCustomer(Integer id);
}
