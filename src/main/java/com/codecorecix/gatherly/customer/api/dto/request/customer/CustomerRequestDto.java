package com.codecorecix.gatherly.customer.api.dto.request.customer;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomerRequestDto implements Serializable {

  private Integer id;
  private String name;
  private String lastname;
  private String phone;
  private String email;
  private String address;
}
