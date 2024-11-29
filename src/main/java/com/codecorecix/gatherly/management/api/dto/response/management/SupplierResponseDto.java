package com.codecorecix.gatherly.management.api.dto.response.management;

import lombok.Data;

@Data
public class SupplierResponseDto {

  private Integer id;
  private String name;
  private String serviceType;
  private Boolean availability;
  private Double servicePrice;
  private String rating;
}
