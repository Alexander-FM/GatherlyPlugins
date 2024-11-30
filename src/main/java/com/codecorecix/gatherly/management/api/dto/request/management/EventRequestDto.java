package com.codecorecix.gatherly.management.api.dto.request.management;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventRequestDto {
  private Integer customerId;
  private Integer supplierId;
  private String eventType;
  private LocalDate eventDate;
  private String location;
  private Double basePrice;
  private List<Integer> serviceIds; // IDs de los servicios seleccionados
}
