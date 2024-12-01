package com.codecorecix.gatherly.management.api.dto.request.management;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ServiceRequestDto {

  @NotBlank(message = "Service name cannot be blank")
  private String serviceName;

  @NotBlank(message = "Description cannot be blank")
  private String description;

  @NotBlank(message = "Quantity cannot be blank")
  private Integer quantity;

  @NotNull(message = "Availability must be specified")
  private Boolean availability;

  @NotNull(message = "Cost is required")
  @Positive(message = "Cost must be greater than 0")
  private Double cost;

  @NotNull(message = "Supplier ID is required")
  private Integer supplierId;
}