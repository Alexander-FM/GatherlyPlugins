package com.codecorecix.gatherly.management.controller;

import com.codecorecix.gatherly.management.api.dto.request.management.EventRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.EventResponseDto;
import com.codecorecix.gatherly.management.service.EventService;
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
  public ResponseEntity<EventResponseDto> createEvent(@RequestBody EventRequestDto request) {
    return ResponseEntity.ok(eventService.createEvent(request));
  }

  @GetMapping
  public ResponseEntity<List<EventResponseDto>> getAllEvents() {
    return ResponseEntity.ok(eventService.getAllEvents());
  }

  @GetMapping("/{id}")
  public ResponseEntity<EventResponseDto> getEventById(@PathVariable Integer id) {
    return ResponseEntity.ok(eventService.getEventById(id));
  }
}
