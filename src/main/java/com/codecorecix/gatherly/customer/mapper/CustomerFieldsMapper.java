package com.codecorecix.gatherly.customer.mapper;

import java.util.List;

import com.codecorecix.gatherly.customer.api.dto.request.CustomerRequestDto;
import com.codecorecix.gatherly.customer.api.dto.response.CustomerResponseDto;
import com.codecorecix.gatherly.entities.Customer;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerFieldsMapper {

  Customer sourceToDestination(final CustomerRequestDto customerRequestDto);

  CustomerResponseDto destinationToSource(final Customer customer);

  List<CustomerResponseDto> sourceToDestination(final List<Customer> customers);
}
