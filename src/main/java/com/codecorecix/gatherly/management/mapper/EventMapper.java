package com.codecorecix.gatherly.management.mapper;

import com.codecorecix.gatherly.entities.Event;
import com.codecorecix.gatherly.entities.Services;
import com.codecorecix.gatherly.management.api.dto.request.management.EventRequestDto;
import com.codecorecix.gatherly.management.api.dto.response.management.EventResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface EventMapper {

  @Mapping(target = "customerId", source = "customer.id")
  @Mapping(target = "customerName", source = "customer.name")
  @Mapping(target = "supplierId", source = "supplier.id")
  @Mapping(target = "supplierName", source = "supplier.name")
  @Mapping(target = "services", source = "includedServices", qualifiedByName = "mapServices")
  EventResponseDto toDto(Event event);

  @Named("mapServices")
  static List<EventResponseDto.ServiceDetail> mapServices(List<Services> services) {
    if (services == null) return null;
    return services.stream()
      .map(service -> {
        EventResponseDto.ServiceDetail detail = new EventResponseDto.ServiceDetail();
        detail.setServiceName(service.getServiceName());
        detail.setCost(service.getCost());
        return detail;
      })
      .collect(Collectors.toList());
  }
}