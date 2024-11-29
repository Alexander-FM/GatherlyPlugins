package com.codecorecix.gatherly.customer.controller;

import com.codecorecix.gatherly.customer.api.dto.request.customer.CustomerRequestDto;
import com.codecorecix.gatherly.customer.api.dto.response.customer.CustomerResponseDto;
import com.codecorecix.gatherly.customer.service.CustomerService;

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

  @PostMapping("/register")
  public ResponseEntity<CustomerResponseDto> registerCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
    // Llamada al servicio para registrar el cliente
    CustomerResponseDto response = customerService.registerCustomer(customerRequestDto);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
    List<CustomerResponseDto> customers = customerService.getAllCustomers();
    return new ResponseEntity<>(customers, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerResponseDto> updateCustomer(
    @PathVariable Integer id,
    @RequestBody CustomerRequestDto customerRequestDto) {
    CustomerResponseDto updatedCustomer = customerService.updateCustomer(id, customerRequestDto);
    return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
    customerService.deleteCustomer(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

