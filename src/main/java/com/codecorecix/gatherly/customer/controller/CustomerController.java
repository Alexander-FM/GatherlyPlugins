package com.codecorecix.gatherly.customer.controller;

import com.codecorecix.gatherly.customer.api.dto.request.customer.CustomerRequestDto;
import com.codecorecix.gatherly.customer.api.dto.response.customer.CustomerResponseDto;
import com.codecorecix.gatherly.customer.service.CustomerService;

import com.codecorecix.gatherly.management.utils.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping
  public GenericResponse<CustomerResponseDto> registerCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
    return customerService.registerCustomer(customerRequestDto);
  }

  @GetMapping
  public GenericResponse<List<CustomerResponseDto>> getAllCustomers() {
    return customerService.getAllCustomers();
  }

  @PutMapping("/{id}")
  public GenericResponse<CustomerResponseDto> updateCustomer(@PathVariable Integer id,
                                                             @RequestBody CustomerRequestDto customerRequestDto) {
    return customerService.updateCustomer(id, customerRequestDto);
  }

  @DeleteMapping("/{id}")
  public GenericResponse<Void> deleteCustomer(@PathVariable Integer id) {
    return customerService.deleteCustomer(id);
  }
}

