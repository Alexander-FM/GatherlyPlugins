package com.codecorecix.gatherly.management.mapper;

import com.codecorecix.gatherly.entities.Event;
import com.codecorecix.gatherly.management.api.dto.request.management.EventRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.EventResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "customer", ignore = true)
  @Mapping(target = "quotation", ignore = true)
  @Mapping(target = "schedule", ignore = true)
  @Mapping(target = "supplier", ignore = true)
  Event toEntity(EventRequestDto requestDto);

  @Mapping(target = "customerId", ignore = true)
  @Mapping(target = "quotationId", ignore = true)
  EventResponseDto toDto(Event event);
}
