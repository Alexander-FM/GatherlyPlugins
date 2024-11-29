package com.codecorecix.gatherly.management.api.dto.request.management;

import lombok.Data;

@Data
public class SupplierRequestDto {

  private String name;
  private String serviceType;
  private Boolean availability;
  private Double servicePrice;
  private String rating;
}
