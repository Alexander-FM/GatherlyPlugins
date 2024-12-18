package com.codecorecix.gatherly.customer.mapper;

import java.util.List;

import com.codecorecix.gatherly.customer.api.dto.request.customer.CustomerRequestDto;
import com.codecorecix.gatherly.customer.api.dto.response.customer.CustomerResponseDto;
import com.codecorecix.gatherly.entities.Customer;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerFieldsMapper {

  Customer sourceToDestination(final CustomerRequestDto customerRequestDto);

  CustomerResponseDto destinationToSource(final Customer customer);
}