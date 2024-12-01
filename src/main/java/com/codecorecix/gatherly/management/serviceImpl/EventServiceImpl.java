package com.codecorecix.gatherly.management.serviceImpl;

import com.codecorecix.gatherly.customer.repository.CustomerRepository;
import com.codecorecix.gatherly.entities.*;
import com.codecorecix.gatherly.exceptions.GatherlyExceptions;
import com.codecorecix.gatherly.management.api.dto.request.management.EventRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.EventResponseDto;
import com.codecorecix.gatherly.management.mapper.EventMapper;
import com.codecorecix.gatherly.management.repository.*;
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
  private final DetalleServiceRepository detalleServiceRepository;

  @Override
  public List<EventResponseDto> getAllEvents() {
    List<Event> events = eventRepository.findAll();
    return events.stream()
      .map(event -> {
        EventResponseDto response = eventMapper.toDto(event);
        double totalCost = calculateTotalCost(event);
        response.setTotalCost(totalCost);
        return response;
      })
      .collect(Collectors.toList());
  }

  @Override
  public EventResponseDto getEventById(Integer id) {
    Event event = eventRepository.findByIdWithDetalleServices(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.EVENT_NOT_FOUND));

    EventResponseDto response = eventMapper.toDto(event);
    response.setTotalCost(calculateTotalCost(event));
    return response;
  }

  @Override
  public EventResponseDto createEvent(EventRequestDto request) {
    // Validar cliente
    Customer customer = customerRepository.findById(request.getCustomerId())
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.CUSTOMER_NOT_FOUND));

    // Validar proveedor
    Supplier supplier = supplierRepository.findById(request.getSupplierId())
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.SUPPLIER_NOT_FOUND));

    // Verificar disponibilidad del proveedor
    if (supplier.getReservationsPerDay().getOrDefault(request.getEventDate(), 0) >= 3) {
      throw new GatherlyExceptions(GatherlyErrorMessage.SUPPLIER_NOT_AVAILABLE);
    }

    // Crear evento
    Event event = new Event();
    event.setCustomer(customer);
    event.setSupplier(supplier);
    event.setEventType(request.getEventType());
    event.setEventDate(request.getEventDate());
    event.setLocation(request.getLocation());
    event.setBasePrice(request.getBasePrice());

    // Crear cotización inicial
    Quotation quotation = new Quotation();
    quotation.setIssueDate(LocalDate.now());
    quotation.setTotalCost(request.getBasePrice());
    event.setQuotation(quotation);

    // Guardar evento inicialmente
    Event savedEvent = eventRepository.save(event);

    // Manejar servicios seleccionados
    if (request.getServices() != null && !request.getServices().isEmpty()) {
      for (EventRequestDto.ServiceRequest serviceRequest : request.getServices()) {
        Services service = serviceRepository.findById(serviceRequest.getServiceId())
          .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.SERVICE_NOT_FOUND));

        // Validar disponibilidad del servicio
        if (!service.getAvailability() || service.getQuantity() < serviceRequest.getQuantity()) {
          throw new GatherlyExceptions(GatherlyErrorMessage.SERVICE_NOT_AVAILABLE);
        }

        // Crear detalle del servicio
        DetalleService detalleService = new DetalleService();
        detalleService.setEvent(savedEvent);
        detalleService.setService(service);
        detalleService.setUsedQuantity(serviceRequest.getQuantity());

        // Reducir cantidad disponible del servicio
        service.reduceQuantity(serviceRequest.getQuantity());

        // Guardar detalle del servicio
        detalleServiceRepository.save(detalleService);

        // Actualizar el costo total de la cotización
        quotation.setTotalCost(quotation.getTotalCost() + (service.getCost() * serviceRequest.getQuantity()));
      }
    }

    // Registrar la reserva del proveedor
    supplier.addReservation(request.getEventDate());

    // Actualizar cotización y guardar evento final
    savedEvent.setQuotation(quotation);
    Event finalEvent = eventRepository.findByIdWithDetalleServices(savedEvent.getId())
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.EVENT_NOT_FOUND));

    // Mapear respuesta
    EventResponseDto response = eventMapper.toDto(finalEvent);
    response.setTotalCost(calculateTotalCost(finalEvent));
    return response;
  }



  @Override
  public EventResponseDto updateEvent(Integer id, EventRequestDto request) {
    Event existingEvent = eventRepository.findById(id)
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.EVENT_NOT_FOUND));

    // Actualizar detalles del evento
    existingEvent.setEventType(request.getEventType());
    existingEvent.setEventDate(request.getEventDate());
    existingEvent.setLocation(request.getLocation());
    existingEvent.setBasePrice(request.getBasePrice());

    // Validar cliente
    Customer customer = customerRepository.findById(request.getCustomerId())
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.CUSTOMER_NOT_FOUND));
    existingEvent.setCustomer(customer);

    // Validar proveedor
    Supplier supplier = supplierRepository.findById(request.getSupplierId())
      .orElseThrow(() -> new GatherlyExceptions(GatherlyErrorMessage.SUPPLIER_NOT_FOUND));
    existingEvent.setSupplier(supplier);
/*
    // Actualizar servicios relacionados (si se envían)
    if (request.getServiceIds() != null && !request.getServiceIds().isEmpty()) {
      List<DetalleService> currentDetails = detalleServiceRepository.findByEvent(existingEvent);
      detalleServiceRepository.deleteAll(currentDetails);

      List<Services> services = serviceRepository.findAllById(request.getServiceIds());
      for (Services service : services) {
        if (!service.getAvailability() || service.getQuantity() <= 0) {
          throw new GatherlyExceptions(GatherlyErrorMessage.SERVICE_NOT_AVAILABLE);
        }

        DetalleService detalleService = new DetalleService();
        detalleService.setEvent(existingEvent);
        detalleService.setService(service);
        detalleService.setUsedQuantity(1);
        service.reduceQuantity(1);
        detalleServiceRepository.save(detalleService);
      }
    }

 */

    // Actualizar cotización
    Quotation quotation = existingEvent.getQuotation();
    double totalCost = calculateTotalCost(existingEvent);
    quotation.setTotalCost(totalCost);
    existingEvent.setQuotation(quotation);

    // Guardar cambios
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

  private double calculateTotalCost(Event event) {
    double totalCost = event.getBasePrice();
    List<DetalleService> detalles = detalleServiceRepository.findByEvent(event);
    if (detalles != null) {
      totalCost += detalles.stream()
        .mapToDouble(ds -> ds.getService().getCost() * ds.getUsedQuantity())
        .sum();
    }
    return totalCost;
  }
}
