package com.codecorecix.gatherly.customer.serviceImpl;

import com.codecorecix.gatherly.customer.mapper.CustomerFieldsMapper;
import com.codecorecix.gatherly.customer.repository.CustomerRepository;

import com.codecorecix.gatherly.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  private final CustomerFieldsMapper customerFieldsMapper;




}
