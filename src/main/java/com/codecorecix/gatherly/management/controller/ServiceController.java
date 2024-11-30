package com.codecorecix.gatherly.management.controller;

import com.codecorecix.gatherly.management.api.dto.request.management.ServiceRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.ServiceResponseDto;
import com.codecorecix.gatherly.management.service.ServiceService;
import com.codecorecix.gatherly.management.utils.GenericResponse;
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
  public ResponseEntity<GenericResponse<ServiceResponseDto>> createService(@RequestBody ServiceRequestDto request) {
    return ResponseEntity.ok(serviceService.createService(request));
  }

  @GetMapping
  public ResponseEntity<GenericResponse<List<ServiceResponseDto>>> getAllServices() {
    return ResponseEntity.ok(serviceService.getAllServices());
  }

  @PutMapping("/{id}")
  public ResponseEntity<GenericResponse<ServiceResponseDto>> updateService(
    @PathVariable Integer id, @RequestBody ServiceRequestDto request) {
    return ResponseEntity.ok(serviceService.updateService(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<GenericResponse<Void>> deleteService(@PathVariable Integer id) {
    return ResponseEntity.ok(serviceService.deleteService(id));
  }
}