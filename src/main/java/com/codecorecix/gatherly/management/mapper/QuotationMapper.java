package com.codecorecix.gatherly.management.mapper;

import com.codecorecix.gatherly.entities.Quotation;
import com.codecorecix.gatherly.management.api.dto.request.management.QuotationRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.QuotationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuotationMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "event", ignore = true)
  @Mapping(target = "totalCost", ignore = true)
  Quotation toEntity(QuotationRequestDto dto);

  @Mapping(target = "eventId", source = "event.id")
  QuotationResponseDto toDto(Quotation entity);
}


