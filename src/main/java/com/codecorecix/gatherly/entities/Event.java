package com.codecorecix.gatherly.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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

  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<DetalleService> detalleServices = new ArrayList<>();


  public void reserveServices() {
    if (detalleServices != null && !detalleServices.isEmpty()) {
      detalleServices.forEach(detalle -> detalle.getService().reduceQuantity(detalle.getUsedQuantity()));
    }
    supplier.addReservation(eventDate);
  }
}
