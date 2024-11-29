package com.codecorecix.gatherly.customer.service;

import com.codecorecix.gatherly.customer.mapper.CustomerFieldsMapper;
import com.codecorecix.gatherly.customer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  private final CustomerFieldsMapper customerFieldsMapper;
}
