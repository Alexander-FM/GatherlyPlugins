package com.codecorecix.gatherly.management.service;


import com.codecorecix.gatherly.management.api.dto.request.management.ServiceRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.ServiceResponseDto;
import com.codecorecix.gatherly.management.utils.GenericResponse;

import java.util.List;

public interface ServiceService {

  GenericResponse<ServiceResponseDto> createService(ServiceRequestDto request);

  GenericResponse<List<ServiceResponseDto>> getAllServices();

  GenericResponse<ServiceResponseDto> updateService(Integer id, ServiceRequestDto request);

  GenericResponse<Void> deleteService(Integer id);
}

