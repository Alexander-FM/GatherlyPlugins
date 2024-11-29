package com.codecorecix.gatherly.utils.errorhandler;

import com.codecorecix.gatherly.exceptions.GatherlyExceptions;
import com.codecorecix.gatherly.utils.GatherlyErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  public record ErrorResponse(Integer code, String message) {

  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public Object exception(final Exception ex) {
    return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
  }

  @ExceptionHandler(GatherlyExceptions.class)
  public ResponseEntity<Object> handleMaintenanceException(GatherlyExceptions ex) {
    GatherlyErrorMessage errorMessage = ex.getErrorMessage();
    HttpStatus status = switch (errorMessage) {
      case ERROR_INTERNAL -> HttpStatus.BAD_REQUEST;
      case ERROR_REGISTER -> HttpStatus.NOT_FOUND;
      case ERROR_LOGIN -> HttpStatus.UNAUTHORIZED;
    };
    return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), errorMessage.getErrorMessage()), status);
  }
}
