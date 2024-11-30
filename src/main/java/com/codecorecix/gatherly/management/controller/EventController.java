package com.codecorecix.gatherly.management.controller;

import com.codecorecix.gatherly.management.api.dto.request.management.EventRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.EventResponseDto;
import com.codecorecix.gatherly.management.service.EventService;
import com.codecorecix.gatherly.management.utils.GenericResponse;
import com.codecorecix.gatherly.management.utils.GenericResponseConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

  private final EventService eventService;

  @PostMapping
  public ResponseEntity<GenericResponse<EventResponseDto>> createEvent(@RequestBody EventRequestDto request) {
    EventResponseDto response = eventService.createEvent(request);
    return ResponseEntity.ok(
      new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, response)
    );
  }

  @GetMapping
  public ResponseEntity<GenericResponse<List<EventResponseDto>>> getAllEvents() {
    List<EventResponseDto> events = eventService.getAllEvents();
    return ResponseEntity.ok(
      new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, events)
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<GenericResponse<EventResponseDto>> getEventById(@PathVariable Integer id) {
    EventResponseDto response = eventService.getEventById(id);
    return ResponseEntity.ok(
      new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, response)
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<GenericResponse<EventResponseDto>> updateEvent(
    @PathVariable Integer id,
    @RequestBody EventRequestDto request
  ) {
    EventResponseDto response = eventService.updateEvent(id, request);
    return ResponseEntity.ok(
      new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, response)
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<GenericResponse<Void>> deleteEvent(@PathVariable Integer id) {
    eventService.deleteEvent(id);
    return ResponseEntity.ok(
      new GenericResponse<>(GenericResponseConstants.SUCCESS, GenericResponseConstants.OPERATION_SUCCESS, null)
    );
  }

}
