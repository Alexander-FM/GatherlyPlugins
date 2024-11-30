package com.codecorecix.gatherly.management.api.dto.request.management;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SupplierRequestDto {

  @NotBlank(message = "Supplier name cannot be blank")
  private String name;

  @NotBlank(message = "Service type cannot be blank")
  private String serviceType;

  @NotNull(message = "Availability must be specified")
  private Boolean availability;

  @NotNull(message = "Service price is required")
  @Positive(message = "Service price must be greater than 0")
  private Double servicePrice;

  @NotBlank(message = "Rating cannot be blank")
  private String rating;
}