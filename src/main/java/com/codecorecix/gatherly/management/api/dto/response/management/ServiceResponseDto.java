package com.codecorecix.gatherly.management.api.dto.response.management;

import lombok.Data;

@Data
public class ServiceResponseDto {

  private Integer id;
  private String serviceName;
  private String description;
  private Boolean availability;
  private Double cost;
  private Integer supplierId;
  private Integer quotationId;
}