package com.codecorecix.gatherly.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GatherlyErrorMessage {

  ERROR_INTERNAL(100, "An error occurred on the server"),
  ERROR_REGISTER(101, "The client already exists"),
  ERROR_LOGIN(102, "The email or password is incorrect");

  private final Integer errorCode;

  private final String errorMessage;
}
