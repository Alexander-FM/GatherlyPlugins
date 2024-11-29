package com.codecorecix.gatherly.management.mapper;

import com.codecorecix.gatherly.entities.Quotation;
import com.codecorecix.gatherly.management.api.dto.request.management.QuotationRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.QuotationResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuotationMapper {
  Quotation toEntity(QuotationRequestDto dto);
  QuotationResponseDto toDto(Quotation entity);
}
