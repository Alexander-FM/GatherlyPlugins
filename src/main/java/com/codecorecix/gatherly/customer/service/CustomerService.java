package com.codecorecix.gatherly.customer.service;

import com.codecorecix.gatherly.customer.api.dto.request.customer.CustomerRequestDto;
import com.codecorecix.gatherly.customer.api.dto.response.customer.CustomerResponseDto;

public interface CustomerService {

  CustomerResponseDto registerCustomer(CustomerRequestDto customerRequestDto);

}
