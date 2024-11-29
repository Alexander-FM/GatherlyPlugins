package com.codecorecix.gatherly.management.controller;

import com.codecorecix.gatherly.management.api.dto.request.management.QuotationRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.QuotationResponseDto;
import com.codecorecix.gatherly.management.service.QuotationService;
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
  public ResponseEntity<QuotationResponseDto> createQuotation(@RequestBody QuotationRequestDto request) {
    return ResponseEntity.ok(quotationService.createQuotation(request));
  }

}

