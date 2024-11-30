package com.codecorecix.gatherly.management.service;

import com.codecorecix.gatherly.management.api.dto.request.management.SupplierRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.SupplierResponseDto;
import com.codecorecix.gatherly.management.utils.GenericResponse;

import java.util.List;

public interface SupplierService {
  GenericResponse<SupplierResponseDto> createSupplier(SupplierRequestDto request);

  GenericResponse<List<SupplierResponseDto>> getAllSuppliers();

  GenericResponse<SupplierResponseDto> updateSupplier(Integer id, SupplierRequestDto request);

  GenericResponse<Void> deleteSupplier(Integer id);
}
