package com.codecorecix.gatherly.management.mapper;

import com.codecorecix.gatherly.entities.Services;
import com.codecorecix.gatherly.management.api.dto.request.management.ServiceRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.ServiceResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = { SupplierMapper.class })
public interface ServiceMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "supplier", ignore = true) // Ignora supplier si no necesitas mapearlo en la entidad
  Services toEntity(ServiceRequestDto dto);

  @Mapping(target = "supplier", source = "supplier") // Usa SupplierMapper para mapear esta relación
  ServiceResponseDto toDto(Services entity);
}
