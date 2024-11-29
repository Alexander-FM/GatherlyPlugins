package com.codecorecix.gatherly.management.service;


import com.codecorecix.gatherly.management.api.dto.request.management.ServiceRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.ServiceResponseDto;

import java.util.List;

public interface ServiceService {

  ServiceResponseDto createService(ServiceRequestDto request);

  List<ServiceResponseDto> getAllServices();
}

