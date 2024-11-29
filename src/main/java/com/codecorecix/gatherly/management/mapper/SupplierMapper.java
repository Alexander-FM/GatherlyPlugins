package com.codecorecix.gatherly.management.mapper;

import com.codecorecix.gatherly.entities.Supplier;
import com.codecorecix.gatherly.management.api.dto.request.management.SupplierRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.SupplierResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
  Supplier toEntity(SupplierRequestDto dto);
  SupplierResponseDto toDto(Supplier entity);
}
