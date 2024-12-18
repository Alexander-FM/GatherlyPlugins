package com.codecorecix.gatherly.management.mapper;

import com.codecorecix.gatherly.entities.Supplier;
import com.codecorecix.gatherly.management.api.dto.request.management.SupplierRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.SupplierResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "services", ignore = true)
  @Mapping(target = "events", ignore = true)
  @Mapping(target = "reservationsPerDay", ignore = true) // Ignorar si no necesitas esta propiedad en el DTO
  Supplier toEntity(SupplierRequestDto requestDto);

  @Mapping(target = "servicePrice", source = "servicePrice") // Mapear correctamente servicePrice
  SupplierResponseDto toDto(Supplier supplier);
}
