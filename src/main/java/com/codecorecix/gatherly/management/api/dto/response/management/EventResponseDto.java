package com.codecorecix.gatherly.management.api.dto.response.management;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventResponseDto {

  private Integer id;
  private String eventType;
  private LocalDate eventDate;
  private String location;
  private Integer customerId;
  private Integer quotationId;
}
