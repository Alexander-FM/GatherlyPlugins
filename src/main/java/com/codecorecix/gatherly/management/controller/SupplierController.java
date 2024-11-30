package com.codecorecix.gatherly.management.controller;

import com.codecorecix.gatherly.management.api.dto.request.management.SupplierRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.SupplierResponseDto;
import com.codecorecix.gatherly.management.service.SupplierService;
import com.codecorecix.gatherly.management.utils.GenericResponse;
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
  public ResponseEntity<GenericResponse<SupplierResponseDto>> createSupplier(@RequestBody SupplierRequestDto request) {
    return ResponseEntity.ok(supplierService.createSupplier(request));
  }

  @GetMapping
  public ResponseEntity<GenericResponse<List<SupplierResponseDto>>> getAllSuppliers() {
    return ResponseEntity.ok(supplierService.getAllSuppliers());
  }

  @PutMapping("/{id}")
  public ResponseEntity<GenericResponse<SupplierResponseDto>> updateSupplier(
    @PathVariable Integer id, @RequestBody SupplierRequestDto request) {
    return ResponseEntity.ok(supplierService.updateSupplier(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<GenericResponse<Void>> deleteSupplier(@PathVariable Integer id) {
    return ResponseEntity.ok(supplierService.deleteSupplier(id));
  }
}