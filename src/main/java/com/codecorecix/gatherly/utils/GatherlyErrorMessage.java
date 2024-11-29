package com.codecorecix.gatherly.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GatherlyErrorMessage {

  ERROR_INTERNAL(100, "Ocurrió un error en el servidor"),
  ERROR_REGISTER(101, "El usuario ya existe"),
  ERROR_LOGIN(102, "El email o contraseña son incorrectas");

  private final Integer errorCode;

  private final String errorMessage;
}
