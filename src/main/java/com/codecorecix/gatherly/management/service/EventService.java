package com.codecorecix.gatherly.management.service;

import com.codecorecix.gatherly.management.api.dto.request.management.EventRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.EventResponseDto;

import java.util.List;

public interface EventService {
  List<EventResponseDto> getAllEvents();
  EventResponseDto getEventById(Integer id);

  EventResponseDto createEvent(EventRequestDto request);

  EventResponseDto updateEvent(Integer id, EventRequestDto request);

  void deleteEvent(Integer id);
}

