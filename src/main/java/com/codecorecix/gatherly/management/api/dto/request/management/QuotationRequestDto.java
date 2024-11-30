package com.codecorecix.gatherly.management.api.dto.request.management;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class QuotationRequestDto {

  private LocalDate issueDate;
  private List<Integer> serviceIds; // Opcional: IDs de servicios asociados
  private Double totalCost; // Agregar este campo para permitir actualizarlo
  private Integer eventId; // Asociar un evento a la cotización

}

