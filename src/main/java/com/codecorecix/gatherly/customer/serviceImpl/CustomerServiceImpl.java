package com.codecorecix.gatherly.customer.serviceImpl;

import com.codecorecix.gatherly.customer.api.dto.request.customer.CustomerRequestDto;
import com.codecorecix.gatherly.customer.api.dto.response.customer.CustomerResponseDto;
import com.codecorecix.gatherly.customer.mapper.CustomerFieldsMapper;
import com.codecorecix.gatherly.customer.repository.CustomerRepository;

import com.codecorecix.gatherly.customer.service.CustomerService;
import com.codecorecix.gatherly.entities.Customer;
import com.codecorecix.gatherly.exceptions.GatherlyExceptions;
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
  public CustomerResponseDto registerCustomer(CustomerRequestDto customerRequestDto) {
    // Verificar si el email ya existe
    if (customerRepository.existsByEmail(customerRequestDto.getEmail())) {
      throw new GatherlyExceptions(GatherlyErrorMessage.ERROR_REGISTER);
    }

    // Convertir el DTO a la entidad
    Customer customer = customerFieldsMapper.sourceToDestination(customerRequestDto);

    // Guardar el cliente en la base de datos
    Customer savedCustomer = customerRepository.save(customer);

    // Convertir la entidad guardada a DTO de respuesta
    return customerFieldsMapper.destinationToSource(savedCustomer);
  }

  @Override
  public List<CustomerResponseDto> getAllCustomers() {
    return customerRepository.findAll().stream()
      .map(customerFieldsMapper::destinationToSource)
      .collect(Collectors.toList());
  }

  @Override
  public CustomerResponseDto updateCustomer(Integer id, CustomerRequestDto customerRequestDto) {
    Customer customer = customerRepository.findById(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.ERROR_INTERNAL));

    customer.setName(customerRequestDto.getName());
    customer.setLastname(customerRequestDto.getLastname());
    customer.setPhone(customerRequestDto.getPhone());
    customer.setEmail(customerRequestDto.getEmail());
    customer.setAddress(customerRequestDto.getAddress());

    Customer updatedCustomer = customerRepository.save(customer);
    return customerFieldsMapper.destinationToSource(updatedCustomer);
  }

  @Override
  public void deleteCustomer(Integer id) {
    Customer customer = customerRepository.findById(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.ERROR_INTERNAL));
    customerRepository.delete(customer);
  }
}