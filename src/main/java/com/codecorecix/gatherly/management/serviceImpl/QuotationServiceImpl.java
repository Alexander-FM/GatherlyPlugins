package com.codecorecix.gatherly.management.serviceImpl;

import com.codecorecix.gatherly.entities.Event;
import com.codecorecix.gatherly.entities.Quotation;
import com.codecorecix.gatherly.entities.Services;
import com.codecorecix.gatherly.exceptions.GatherlyExceptions;
import com.codecorecix.gatherly.management.api.dto.request.management.QuotationRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.QuotationResponseDto;
import com.codecorecix.gatherly.management.mapper.QuotationMapper;
import com.codecorecix.gatherly.management.repository.QuotationRepository;
import com.codecorecix.gatherly.management.repository.ServiceRepository;
import com.codecorecix.gatherly.management.service.QuotationService;
import com.codecorecix.gatherly.utils.GatherlyErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuotationServiceImpl implements QuotationService {

  private final QuotationRepository quotationRepository;
  private final ServiceRepository serviceRepository; // Repositorio para recuperar servicios
  private final QuotationMapper quotationMapper;

  @Override
  public QuotationResponseDto createQuotation(QuotationRequestDto request) {
    // Crear la cotización
    Quotation quotation = new Quotation();
    quotation.setIssueDate(request.getIssueDate());

    // Validar y asociar servicios
    List<Services> services = serviceRepository.findAllById(request.getServiceIds());
    if (services.isEmpty()) {
      throw new GatherlyExceptions(GatherlyErrorMessage.ERROR_INTERNAL);
    }



    // Guardar la cotización
    Quotation savedQuotation = quotationRepository.save(quotation);
    return quotationMapper.toDto(savedQuotation);
  }


  @Override
  public List<QuotationResponseDto> getAllQuotations() {
    return quotationRepository.findAll().stream()
      .map(quotation -> {
        QuotationResponseDto dto = quotationMapper.toDto(quotation);
        // Asociar eventId si el evento está relacionado
        if (quotation.getEvent() != null) {
          dto.setEventId(quotation.getEvent().getId());
        }
        return dto;
      })
      .collect(Collectors.toList());
  }


  @Override
  public QuotationResponseDto getQuotationById(Integer id) {
    Quotation quotation = quotationRepository.findById(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.QUOTATION_NOT_FOUND));

    QuotationResponseDto dto = quotationMapper.toDto(quotation);
    if (quotation.getEvent() != null) {
      dto.setEventId(quotation.getEvent().getId());
    }
    return dto;
  }

  @Override
  public void deleteQuotation(Integer id) {
    Quotation quotation = quotationRepository.findById(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.QUOTATION_NOT_FOUND));
    quotationRepository.delete(quotation);
  }
}
