package com.codecorecix.gatherly.management.serviceImpl;

import com.codecorecix.gatherly.exceptions.GatherlyExceptions;
import com.codecorecix.gatherly.management.api.dto.request.management.SupplierRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.SupplierResponseDto;
import com.codecorecix.gatherly.management.mapper.SupplierMapper;
import com.codecorecix.gatherly.management.repository.SupplierRepository;
import com.codecorecix.gatherly.entities.Supplier;
import com.codecorecix.gatherly.management.service.SupplierService;
import com.codecorecix.gatherly.management.utils.GenericResponse;
import com.codecorecix.gatherly.management.utils.GenericResponseConstants;
import com.codecorecix.gatherly.utils.GatherlyErrorMessage;
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
  public GenericResponse<SupplierResponseDto> createSupplier(SupplierRequestDto request) {
    // Convertir el DTO a la entidad
    Supplier supplier = supplierMapper.toEntity(request);
    // Guardar la entidad en la base de datos
    Supplier savedSupplier = supplierRepository.save(supplier);
    // Mapear la entidad guardada a DTO de respuesta
    SupplierResponseDto responseDto = supplierMapper.toDto(savedSupplier);

    return new GenericResponse<>(
      GenericResponseConstants.SUCCESS,
      GenericResponseConstants.OPERATION_SUCCESS,
      responseDto
    );
  }

  @Override
  public GenericResponse<List<SupplierResponseDto>> getAllSuppliers() {
    // Obtener todos los proveedores y mapearlos a DTOs
    List<SupplierResponseDto> suppliers = supplierRepository.findAll().stream()
      .map(supplierMapper::toDto)
      .collect(Collectors.toList());

    return new GenericResponse<>(
      GenericResponseConstants.SUCCESS,
      GenericResponseConstants.OPERATION_SUCCESS,
      suppliers
    );
  }

  @Override
  public GenericResponse<SupplierResponseDto> updateSupplier(Integer id, SupplierRequestDto request) {
    Supplier supplier = supplierRepository.findById(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.SUPPLIER_NOT_FOUND));

    supplier.setName(request.getName());
    supplier.setServiceType(request.getServiceType());
    supplier.setAvailability(request.getAvailability());
    supplier.setServicePrice(request.getServicePrice());
    supplier.setRating(request.getRating());

    Supplier updatedSupplier = supplierRepository.save(supplier);
    SupplierResponseDto responseDto = supplierMapper.toDto(updatedSupplier);

    return new GenericResponse<>(
      GenericResponseConstants.SUCCESS,
      GenericResponseConstants.OPERATION_SUCCESS,
      responseDto
    );
  }

  @Override
  public GenericResponse<Void> deleteSupplier(Integer id) {
    Supplier supplier = supplierRepository.findById(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.SUPPLIER_NOT_FOUND));

    supplierRepository.delete(supplier);

    return new GenericResponse<>(
      GenericResponseConstants.SUCCESS,
      GenericResponseConstants.OPERATION_SUCCESS,
      null
    );
  }
}
