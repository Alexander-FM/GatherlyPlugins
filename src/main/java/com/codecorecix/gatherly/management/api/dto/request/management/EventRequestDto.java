package com.codecorecix.gatherly.management.api.dto.request.management;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventRequestDto {

  private String eventType;
  private LocalDate eventDate;
  private String location;
  private Integer customerId;
  private Integer quotationId;
}
