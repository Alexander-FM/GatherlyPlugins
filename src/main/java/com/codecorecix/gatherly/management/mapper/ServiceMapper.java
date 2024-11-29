package com.codecorecix.gatherly.management.mapper;

import com.codecorecix.gatherly.entities.Services;
import com.codecorecix.gatherly.management.api.dto.request.management.ServiceRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.ServiceResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
  Services toEntity(ServiceRequestDto dto);
  ServiceResponseDto toDto(Services entity);
}

