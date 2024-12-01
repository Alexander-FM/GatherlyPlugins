package com.codecorecix.gatherly.management.api.dto.response.management;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventResponseDto {
  private Integer id;
  private Integer customerId;
  private String customerName;
  private Integer supplierId;
  private String supplierName;
  private String eventType;
  private LocalDate eventDate;
  private String location;
  private Double basePrice;
  private List<ServiceDetail> services; // Cambiamos para incluir nombre y costo
  private Double totalCost;

  @Data
  public static class ServiceDetail {
    private String serviceName;
    private Double cost;
    private Integer quantity; // Agregamos el campo quantity

  }
}
