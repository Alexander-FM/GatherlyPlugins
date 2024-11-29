package com.codecorecix.gatherly.customer.api.dto.response.customer;

import lombok.Data;

@Data
public class CustomerResponseDto {
  private Integer id;
  private String name;
  private String lastname;
  private String phone;
  private String email;
  private String address;
}
