package com.codecorecix.gatherly.customer.controller;

import com.codecorecix.gatherly.customer.service.CustomerService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;
}
