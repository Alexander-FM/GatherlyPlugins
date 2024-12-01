package com.codecorecix.gatherly.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier")
public class Supplier implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "service_type", nullable = false)
  private String serviceType;

  @Column(name = "availability", nullable = false)
  private Boolean availability;

  @Column(name = "service_price", nullable = false)
  private Double servicePrice;

  @Column(name = "rating", nullable = false)
  private String rating;

  @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Services> services;

  @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Event> events;

  @ElementCollection
  @CollectionTable(name = "supplier_reservations", joinColumns = @JoinColumn(name = "supplier_id"))
  @MapKeyColumn(name = "reservation_date")
  @Column(name = "reservations_count")
  private Map<LocalDate, Integer> reservationsPerDay = new HashMap<>();

  public void addReservation(LocalDate date) {
    int count = reservationsPerDay.getOrDefault(date, 0);
    if (count >= 3) {
      throw new IllegalArgumentException("Maximum reservations for the day reached.");
    }
    reservationsPerDay.put(date, count + 1);
    updateAvailability();
  }

  public void updateAvailability() {
    this.availability = reservationsPerDay.values().stream().noneMatch(count -> count >= 3);
  }
}

