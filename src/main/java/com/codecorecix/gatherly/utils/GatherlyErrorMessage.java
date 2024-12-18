package com.codecorecix.gatherly.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GatherlyErrorMessage {


  // EMPLOYEE
  EMPLOYEE_ALREADY_EXISTS(101, "The employee already exists"),
  EMPLOYEE_NOT_FOUND(102, "The employee does not exist"),
  EMPLOYEE_INVALID_LOGIN(103, "Invalid email or password"),

  // CUSTOMER
  CUSTOMER_ALREADY_EXISTS(201, "The customer already exists"),
  CUSTOMER_NOT_FOUND(202, "The customer does not exist"),

  // SUPPLIER
  SUPPLIER_ALREADY_EXISTS(301, "The supplier already exists"),
  SUPPLIER_NOT_FOUND(302, "The supplier does not exist"),
  SUPPLIER_NOT_AVAILABLE(303, "The supplier is not available for the selected date"),

  // SERVICES
  SERVICE_NOT_FOUND(401, "The service does not exist"),
  SERVICE_ALREADY_EXISTS(402, "The service already exists"),
  SERVICE_NOT_AVAILABLE(403, "The selected service is not available or out of stock"),

  // QUOTATION
  QUOTATION_NOT_FOUND(501, "The quotation does not exist"),
  QUOTATION_ALREADY_EXISTS(502, "The quotation already exists"),

  // EVENT
  EVENT_NOT_FOUND(601, "The event does not exist"),
  EVENT_ALREADY_EXISTS(602, "The event already exists"),
  SERVICE_OUT_OF_STOCK(603, "One or more selected services are out of stock"),


  // GENERAL
  ERROR_INTERNAL(500, "An error occurred on the server");

  private final Integer errorCode;

  private final String errorMessage;
}
