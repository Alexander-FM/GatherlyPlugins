  package com.codecorecix.gatherly.management.service;

  import com.codecorecix.gatherly.management.api.dto.request.management.QuotationRequestDto;
  import com.codecorecix.gatherly.management.api.dto.response.management.QuotationResponseDto;

  import java.util.List;

  public interface QuotationService {
    QuotationResponseDto createQuotation(QuotationRequestDto request);

    List<QuotationResponseDto> getAllQuotations();

    QuotationResponseDto getQuotationById(Integer id);

    void deleteQuotation(Integer id);
  }
