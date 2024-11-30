package com.codecorecix.gatherly.management.mapper;

import com.codecorecix.gatherly.entities.Services;
import com.codecorecix.gatherly.management.api.dto.request.management.ServiceRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.ServiceResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = SupplierMapper.class)
public interface ServiceMapper {

  @Mapping(target = "supplier", source = "supplier") // Usar el SupplierMapper para mapear el proveedor
  ServiceResponseDto toDto(Services entity);
}