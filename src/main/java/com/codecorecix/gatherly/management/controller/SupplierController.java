package com.codecorecix.gatherly.management.controller;

import com.codecorecix.gatherly.management.api.dto.request.management.SupplierRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.SupplierResponseDto;
import com.codecorecix.gatherly.management.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

  private final SupplierService supplierService;

  @PostMapping
  public ResponseEntity<SupplierResponseDto> createSupplier(@RequestBody SupplierRequestDto request) {
    return ResponseEntity.ok(supplierService.createSupplier(request));
  }

  @GetMapping
  public ResponseEntity<List<SupplierResponseDto>> getAllSuppliers() {
    return ResponseEntity.ok(supplierService.getAllSuppliers());
  }
}