package com.codecorecix.gatherly.management.service;

import com.codecorecix.gatherly.management.api.dto.request.management.SupplierRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.SupplierResponseDto;

import java.util.List;

public interface SupplierService {
  SupplierResponseDto createSupplier(SupplierRequestDto request);
  List<SupplierResponseDto> getAllSuppliers();
}

