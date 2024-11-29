package com.codecorecix.gatherly.management.mapper;

import com.codecorecix.gatherly.entities.Event;
import com.codecorecix.gatherly.management.api.dto.request.management.EventRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.EventResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
  Event toEntity(EventRequestDto dto);
  EventResponseDto toDto(Event entity);
}
