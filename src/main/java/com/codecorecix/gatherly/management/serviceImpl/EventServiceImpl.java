package com.codecorecix.gatherly.management.serviceImpl;

import com.codecorecix.gatherly.customer.repository.CustomerRepository;
import com.codecorecix.gatherly.entities.*;
import com.codecorecix.gatherly.exceptions.GatherlyExceptions;
import com.codecorecix.gatherly.management.api.dto.request.management.EventRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.EventResponseDto;
import com.codecorecix.gatherly.management.mapper.EventMapper;
import com.codecorecix.gatherly.management.repository.EventRepository;
import com.codecorecix.gatherly.management.repository.QuotationRepository;
import com.codecorecix.gatherly.management.repository.ServiceRepository;
import com.codecorecix.gatherly.management.repository.SupplierRepository;
import com.codecorecix.gatherly.management.service.EventService;
import com.codecorecix.gatherly.utils.GatherlyErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;
  private final EventMapper eventMapper;
  private final CustomerRepository customerRepository;
  private final QuotationRepository quotationRepository;
  private final ServiceRepository serviceRepository;
  private final SupplierRepository supplierRepository;

  @Override
  public List<EventResponseDto> getAllEvents() {
    // Obtener todos los eventos desde la base de datos
    List<Event> events = eventRepository.findAll();

    // Mapear cada evento a su correspondiente DTO
    return events.stream()
      .map(event -> {
        EventResponseDto response = eventMapper.toDto(event);

        // Calcular el costo total (basePrice + servicios)
        double totalCost = event.getBasePrice();
        if (event.getIncludedServices() != null) {
          totalCost += event.getIncludedServices()
            .stream()
            .mapToDouble(Services::getCost)
            .sum();
        }
        response.setTotalCost(totalCost);

        return response;
      })
      .collect(Collectors.toList());
  }


  @Override
  public EventResponseDto getEventById(Integer id) {
    Event event = eventRepository.findById(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.EVENT_NOT_FOUND));

    EventResponseDto response = eventMapper.toDto(event);

    double totalCost = event.getBasePrice();
    if (event.getIncludedServices() != null) {
      totalCost += event.getIncludedServices()
        .stream()
        .mapToDouble(Services::getCost)
        .sum();
    }
    response.setTotalCost(totalCost);

    return response;
  }


  @Override
  public EventResponseDto createEvent(EventRequestDto request) {
    // Crear un evento
    Event event = new Event();
    event.setEventType(request.getEventType());
    event.setEventDate(request.getEventDate());
    event.setLocation(request.getLocation());
    event.setBasePrice(request.getBasePrice());

    // Validar el cliente
    Customer customer = customerRepository.findById(request.getCustomerId())
      .orElseThrow(() -> new RuntimeException("Customer not found"));
    event.setCustomer(customer);

    // Validar el proveedor
    Supplier supplier = supplierRepository.findById(request.getSupplierId())
      .orElseThrow(() -> new RuntimeException("Supplier not found"));
    event.setSupplier(supplier);

    // Asociar servicios seleccionados (si los hay)
    List<Services> services = null;
    if (request.getServiceIds() != null && !request.getServiceIds().isEmpty()) {
      services = serviceRepository.findAllById(request.getServiceIds());
      event.setIncludedServices(services);
    }

    // Calcular el precio base y sumar servicios
    double totalCost = event.getBasePrice();
    if (services != null) {
      totalCost += services.stream().mapToDouble(Services::getCost).sum();
    }

    // Crear y asociar la cotización
    Quotation quotation = new Quotation();
    quotation.setIssueDate(LocalDate.now());
    quotation.setTotalCost(totalCost);
    event.setQuotation(quotation);

    // Guardar evento y cotización
    Event savedEvent = eventRepository.save(event);

    // Mapear respuesta
    EventResponseDto response = eventMapper.toDto(savedEvent);
    response.setTotalCost(totalCost);
    return response;
  }

  @Override
  public EventResponseDto updateEvent(Integer id, EventRequestDto request) {
    Event existingEvent = eventRepository.findById(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.EVENT_NOT_FOUND));

    existingEvent.setEventType(request.getEventType());
    existingEvent.setEventDate(request.getEventDate());
    existingEvent.setLocation(request.getLocation());
    existingEvent.setBasePrice(request.getBasePrice());

    Customer customer = customerRepository.findById(request.getCustomerId())
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.CUSTOMER_NOT_FOUND));
    existingEvent.setCustomer(customer);

    Supplier supplier = supplierRepository.findById(request.getSupplierId())
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.SUPPLIER_NOT_FOUND));
    existingEvent.setSupplier(supplier);

    if (request.getServiceIds() != null && !request.getServiceIds().isEmpty()) {
      List<Services> services = serviceRepository.findAllById(request.getServiceIds());
      existingEvent.setIncludedServices(services);
    }

    double totalCost = existingEvent.getBasePrice();
    if (existingEvent.getIncludedServices() != null) {
      totalCost += existingEvent.getIncludedServices()
        .stream()
        .mapToDouble(Services::getCost)
        .sum();
    }

    Quotation quotation = existingEvent.getQuotation();
    quotation.setTotalCost(totalCost);
    existingEvent.setQuotation(quotation);

    Event updatedEvent = eventRepository.save(existingEvent);
    EventResponseDto response = eventMapper.toDto(updatedEvent);
    response.setTotalCost(totalCost);
    return response;
  }

  @Override
  public void deleteEvent(Integer id) {
    Event event = eventRepository.findById(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.EVENT_NOT_FOUND));
    eventRepository.delete(event);
  }


}

