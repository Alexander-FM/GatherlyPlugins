package com.codecorecix.gatherly.management.controller;

import com.codecorecix.gatherly.management.api.dto.request.management.ServiceRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.ServiceResponseDto;
import com.codecorecix.gatherly.management.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

  private final ServiceService serviceService;

  @PostMapping
  public ResponseEntity<ServiceResponseDto> createService(@RequestBody ServiceRequestDto request) {
    return ResponseEntity.ok(serviceService.createService(request));
  }

  @GetMapping
  public ResponseEntity<List<ServiceResponseDto>> getAllServices() {
    return ResponseEntity.ok(serviceService.getAllServices());
  }
}