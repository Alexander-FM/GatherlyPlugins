package com.codecorecix.gatherly.customer.service;

import com.codecorecix.gatherly.customer.api.dto.request.customer.CustomerRequestDto;
import com.codecorecix.gatherly.customer.api.dto.response.customer.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

  CustomerResponseDto registerCustomer(CustomerRequestDto customerRequestDto);

  List<CustomerResponseDto> getAllCustomers();

  CustomerResponseDto updateCustomer(Integer id, CustomerRequestDto customerRequestDto);

  void deleteCustomer(Integer id);
}
