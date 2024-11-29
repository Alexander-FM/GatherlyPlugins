package com.codecorecix.gatherly.management.api.dto.request.management;

import lombok.Data;

@Data
public class ServiceRequestDto {

  private String serviceName;
  private String description;
  private Boolean availability;
  private Double cost;
  private Integer supplierId;
  private Integer quotationId;
}
