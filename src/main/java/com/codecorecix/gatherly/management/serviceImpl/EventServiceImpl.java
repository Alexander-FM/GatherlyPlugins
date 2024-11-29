package com.codecorecix.gatherly.management.serviceImpl;

import com.codecorecix.gatherly.customer.repository.CustomerRepository;
import com.codecorecix.gatherly.entities.Customer;
import com.codecorecix.gatherly.entities.Event;
import com.codecorecix.gatherly.entities.Quotation;
import com.codecorecix.gatherly.management.api.dto.request.management.EventRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.EventResponseDto;
import com.codecorecix.gatherly.management.mapper.EventMapper;
import com.codecorecix.gatherly.management.repository.EventRepository;
import com.codecorecix.gatherly.management.repository.QuotationRepository;
import com.codecorecix.gatherly.management.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;
  private final EventMapper eventMapper;
  private final CustomerRepository customerRepository;
  private final QuotationRepository quotationRepository;

  @Override
  public EventResponseDto createEvent(EventRequestDto request) {
    Event event = eventMapper.toEntity(request);

    // Validar existencia de Customer
    Customer customer = customerRepository.findById(request.getCustomerId())
      .orElseThrow(() -> new RuntimeException("Customer not found"));
    event.setCustomer(customer);

    // Validar existencia de Quotation
    Quotation quotation = quotationRepository.findById(request.getQuotationId())
      .orElseThrow(() -> new RuntimeException("Quotation not found"));
    event.setQuotation(quotation);

    Event savedEvent = eventRepository.save(event);
    return eventMapper.toDto(savedEvent);
  }

  @Override
  public List<EventResponseDto> getAllEvents() {
    return eventRepository.findAll().stream()
      .map(eventMapper::toDto)
      .collect(Collectors.toList());
  }

  @Override
  public EventResponseDto getEventById(Integer id) {
    return eventRepository.findById(id)
      .map(eventMapper::toDto)
      .orElseThrow(() -> new RuntimeException("Event not found"));
  }
}

