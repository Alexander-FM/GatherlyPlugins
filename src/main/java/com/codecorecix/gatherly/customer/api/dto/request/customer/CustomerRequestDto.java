package com.codecorecix.gatherly.customer.api.dto.request.customer;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerRequestDto implements Serializable {

  private Integer id;

  @NotBlank(message = "El nombre es obligatorio")
  private String name;

  @NotBlank(message = "El apellido es obligatorio")
  private String lastname;

  @NotBlank(message = "El teléfono es obligatorio")
  private String phone;

  @Email(message = "El email debe ser válido")
  private String email;

  @NotBlank(message = "La dirección es obligatoria")
  private String address;
}