package com.codecorecix.gatherly.management.controller;

import com.codecorecix.gatherly.management.api.dto.request.management.QuotationRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.EventResponseDto;
import com.codecorecix.gatherly.management.api.dto.response.management.QuotationResponseDto;
import com.codecorecix.gatherly.management.service.QuotationService;
import com.codecorecix.gatherly.management.utils.GenericResponse;
import com.codecorecix.gatherly.management.utils.GenericResponseConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotations")
@RequiredArgsConstructor
public class QuotationController {

  private final QuotationService quotationService;

  @PostMapping
  public ResponseEntity<GenericResponse<QuotationResponseDto>> createQuotation(@RequestBody QuotationRequestDto request) {
    QuotationResponseDto response = quotationService.createQuotation(request);
    return ResponseEntity.ok(
      new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, response)
    );
  }

  @GetMapping
  public ResponseEntity<GenericResponse<List<QuotationResponseDto>>> getAllQuotations() {
    List<QuotationResponseDto> quotations = quotationService.getAllQuotations();
    return ResponseEntity.ok(
      new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, quotations)
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<GenericResponse<QuotationResponseDto>> getQuotationById(@PathVariable Integer id) {
    QuotationResponseDto response = quotationService.getQuotationById(id);
    return ResponseEntity.ok(
      new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, response)
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<GenericResponse<Void>> deleteQuotation(@PathVariable Integer id) {
    quotationService.deleteQuotation(id);
    return ResponseEntity.ok(
      new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, null)
    );
  }
}


