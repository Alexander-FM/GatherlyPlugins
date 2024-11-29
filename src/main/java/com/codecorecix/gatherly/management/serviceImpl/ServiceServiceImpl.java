package com.codecorecix.gatherly.management.serviceImpl;

import com.codecorecix.gatherly.entities.Services;
import com.codecorecix.gatherly.management.api.dto.request.management.ServiceRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.ServiceResponseDto;
import com.codecorecix.gatherly.management.mapper.ServiceMapper;
import com.codecorecix.gatherly.management.repository.ServiceRepository;
import com.codecorecix.gatherly.management.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

  private final ServiceRepository serviceRepository;
  private final ServiceMapper serviceMapper;

  @Override
  public ServiceResponseDto createService(ServiceRequestDto request) {
    Services service = serviceMapper.toEntity(request);
    Services savedService = serviceRepository.save(service);
    return serviceMapper.toDto(savedService);
  }

  @Override
  public List<ServiceResponseDto> getAllServices() {
    return serviceRepository.findAll().stream()
      .map(serviceMapper::toDto)
      .collect(Collectors.toList());
  }
}
