package com.codecorecix.gatherly.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "quotation_id", nullable = false)
  private Quotation quotation;

  @Column(name = "event_type", nullable = false)
  private String eventType;

  @Column(name = "event_date", nullable = false)
  private LocalDate eventDate;

  @Column(name = "location", nullable = false)
  private String location;

  @Column(name = "base_price", nullable = false)
  private Double basePrice;

  @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
  private Schedule schedule;

  @ManyToOne
  @JoinColumn(name = "supplier_id", nullable = false)
  private Supplier supplier;

  @OneToMany
  @JoinColumn(name = "event_id") // Relación directa entre evento y servicios
  private List<Services> includedServices;

  public void calculateBasePrice() {
    // Calcular el precio base del evento basado en los servicios seleccionados
    this.basePrice = includedServices.stream()
      .mapToDouble(Services::getCost)
      .sum() * 0.10; // Ejemplo: 10% del costo de los servicios incluidos
  }
}
