package com.codecorecix.gatherly.exceptions;

import com.codecorecix.gatherly.utils.GatherlyErrorMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GatherlyExceptions extends RuntimeException {
  private final GatherlyErrorMessage errorMessage;

  private final Integer errorCode;

  public GatherlyExceptions(final GatherlyErrorMessage errorMessage) {
    super(errorMessage.getErrorMessage());
    this.errorMessage = errorMessage;
    this.errorCode = errorMessage.getErrorCode();
  }
}
