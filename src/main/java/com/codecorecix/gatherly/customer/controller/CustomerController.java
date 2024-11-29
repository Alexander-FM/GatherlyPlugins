package com.codecorecix.gatherly.customer.controller;

import com.codecorecix.gatherly.customer.api.dto.request.customer.CustomerRequestDto;
import com.codecorecix.gatherly.customer.api.dto.response.customer.CustomerResponseDto;
import com.codecorecix.gatherly.customer.service.CustomerService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping("/register")
  public ResponseEntity<CustomerResponseDto> registerCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
    // Llamada al servicio para registrar el cliente
    CustomerResponseDto response = customerService.registerCustomer(customerRequestDto);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
