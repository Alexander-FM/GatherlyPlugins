package com.codecorecix.gatherly.management.api.dto.response.management;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class QuotationResponseDto {

  private Integer id;
  private LocalDate issueDate;
  private Double totalCost;
  private Integer eventId;
}