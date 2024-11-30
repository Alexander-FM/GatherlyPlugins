package com.codecorecix.gatherly.utils.errorhandler;

import com.codecorecix.gatherly.exceptions.GatherlyExceptions;
import com.codecorecix.gatherly.management.utils.GenericResponse;
import com.codecorecix.gatherly.management.utils.GenericResponseConstants;
import com.codecorecix.gatherly.utils.GatherlyErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public GenericResponse<Object> handleGeneralException(Exception ex) {
    return new GenericResponse<>(
      GenericResponseConstants.ERROR,
      GenericResponseConstants.OPERATION_FAILED,
      ex.getMessage()
    );
  }

  @ExceptionHandler(GatherlyExceptions.class)
  public ResponseEntity<GenericResponse<Object>> handleGatherlyException(GatherlyExceptions ex) {
    GatherlyErrorMessage errorMessage = ex.getErrorMessage();
    HttpStatus status = mapToHttpStatus(errorMessage);

    return new ResponseEntity<>(
      new GenericResponse<>(
        GenericResponseConstants.ERROR,
        errorMessage.getErrorMessage(),
        null
      ),
      status
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public GenericResponse<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return new GenericResponse<>(
      GenericResponseConstants.ERROR,
      "Validation failed",
      errors
    );
  }

  private HttpStatus mapToHttpStatus(GatherlyErrorMessage errorMessage) {
    return switch (errorMessage) {
      case EMPLOYEE_ALREADY_EXISTS, CUSTOMER_ALREADY_EXISTS, SUPPLIER_ALREADY_EXISTS ->
        HttpStatus.CONFLICT;
      case EMPLOYEE_NOT_FOUND, CUSTOMER_NOT_FOUND, SUPPLIER_NOT_FOUND, SERVICE_NOT_FOUND ->
        HttpStatus.NOT_FOUND;
      case EMPLOYEE_INVALID_LOGIN ->
        HttpStatus.UNAUTHORIZED;
      default ->
        HttpStatus.INTERNAL_SERVER_ERROR;
    };
  }
}
