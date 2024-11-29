package com.codecorecix.gatherly.management.serviceImpl;

import com.codecorecix.gatherly.management.api.dto.request.management.SupplierRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.SupplierResponseDto;
import com.codecorecix.gatherly.management.mapper.SupplierMapper;
import com.codecorecix.gatherly.management.repository.SupplierRepository;
import com.codecorecix.gatherly.entities.Supplier;
import com.codecorecix.gatherly.management.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

  private final SupplierRepository supplierRepository;
  private final SupplierMapper supplierMapper;

  @Override
  public SupplierResponseDto createSupplier(SupplierRequestDto request) {
    Supplier supplier = supplierMapper.toEntity(request);
    Supplier savedSupplier = supplierRepository.save(supplier);
    return supplierMapper.toDto(savedSupplier);
  }

  @Override
  public List<SupplierResponseDto> getAllSuppliers() {
    return supplierRepository.findAll().stream()
      .map(supplierMapper::toDto)
      .collect(Collectors.toList());
  }
}

