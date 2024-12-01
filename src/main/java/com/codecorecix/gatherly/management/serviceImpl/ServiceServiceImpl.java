package com.codecorecix.gatherly.management.serviceImpl;

import com.codecorecix.gatherly.entities.Services;
import com.codecorecix.gatherly.entities.Supplier;
import com.codecorecix.gatherly.exceptions.GatherlyExceptions;
import com.codecorecix.gatherly.management.api.dto.request.management.ServiceRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.ServiceResponseDto;
import com.codecorecix.gatherly.management.mapper.ServiceMapper;
import com.codecorecix.gatherly.management.repository.ServiceRepository;
import com.codecorecix.gatherly.management.repository.SupplierRepository;
import com.codecorecix.gatherly.management.service.ServiceService;
import com.codecorecix.gatherly.management.utils.GenericResponse;
import com.codecorecix.gatherly.management.utils.GenericResponseConstants;
import com.codecorecix.gatherly.utils.GatherlyErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

  private final ServiceRepository serviceRepository;
  private final ServiceMapper serviceMapper;
  private final SupplierRepository supplierRepository;

  @Override
  public GenericResponse<ServiceResponseDto> createService(ServiceRequestDto request) {
    // Validar el proveedor
    Supplier supplier = supplierRepository.findById(request.getSupplierId())
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.SUPPLIER_NOT_FOUND));

    // Crear el servicio
    Services service = new Services();
    service.setServiceName(request.getServiceName());
    service.setCost(request.getCost());
    service.setQuantity(request.getQuantity());
    service.setDescription(request.getDescription());
    service.setAvailability(request.getQuantity() > 0);
    service.setSupplier(supplier);

    // Guardar el servicio
    Services savedService = serviceRepository.save(service);
    ServiceResponseDto response = serviceMapper.toDto(savedService);

    return new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, response);
  }

  @Override
  public GenericResponse<List<ServiceResponseDto>> getServicesBySupplier(Integer supplierId) {
    // Validar que el proveedor exista
    Supplier supplier = supplierRepository.findById(supplierId)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.SUPPLIER_NOT_FOUND));

    // Obtener los servicios asociados al proveedor
    List<ServiceResponseDto> services = serviceRepository.findBySupplierId(supplierId).stream()
      .map(serviceMapper::toDto)
      .collect(Collectors.toList());

    return new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, services);
  }


  @Override
  public GenericResponse<List<ServiceResponseDto>> getAllServices() {
    List<ServiceResponseDto> services = serviceRepository.findAll().stream()
      .map(serviceMapper::toDto)
      .collect(Collectors.toList());

    return new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, services);
  }

  @Override
  public GenericResponse<ServiceResponseDto> updateService(Integer id, ServiceRequestDto request) {
    Services service = serviceRepository.findById(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.SERVICE_NOT_FOUND));

    Supplier supplier = supplierRepository.findById(request.getSupplierId())
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.SUPPLIER_NOT_FOUND));

    service.setServiceName(request.getServiceName());
    service.setCost(request.getCost());
    service.setDescription(request.getDescription());
    service.setAvailability(request.getAvailability());
    service.setSupplier(supplier);

    Services updatedService = serviceRepository.save(service);
    ServiceResponseDto response = serviceMapper.toDto(updatedService);

    return new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, response);
  }

  @Override
  public GenericResponse<Void> deleteService(Integer id) {
    Services service = serviceRepository.findById(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.SERVICE_NOT_FOUND));

    serviceRepository.delete(service);

    return new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, null);
  }
}
