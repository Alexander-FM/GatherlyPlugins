package com.codecorecix.gatherly.management.mapper;

import com.codecorecix.gatherly.entities.DetalleService;
import com.codecorecix.gatherly.entities.Event;
import com.codecorecix.gatherly.entities.Services;
import com.codecorecix.gatherly.management.api.dto.request.management.EventRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.EventResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = { ServiceMapper.class, SupplierMapper.class })
public interface EventMapper {

  @Mapping(target = "customerId", source = "customer.id")
  @Mapping(target = "customerName", source = "customer.name")
  @Mapping(target = "supplierId", source = "supplier.id")
  @Mapping(target = "supplierName", source = "supplier.name")
  @Mapping(target = "services", source = "detalleServices", qualifiedByName = "mapDetalleServices")
  @Mapping(target = "totalCost", source = "quotation.totalCost") // Mapear desde quotation
  EventResponseDto toDto(Event event);

  @Named("mapDetalleServices")
  static List<EventResponseDto.ServiceDetail> mapDetalleServices(List<DetalleService> detalleServices) {
    if (detalleServices == null || detalleServices.isEmpty()) {
      return new ArrayList<>();
    }
    return detalleServices.stream()
      .map(detalle -> {
        EventResponseDto.ServiceDetail detail = new EventResponseDto.ServiceDetail();
        detail.setServiceName(detalle.getService().getServiceName());
        detail.setCost(detalle.getService().getCost());
        detail.setQuantity(detalle.getUsedQuantity());
        return detail;
      })
      .collect(Collectors.toList());
  }
}
