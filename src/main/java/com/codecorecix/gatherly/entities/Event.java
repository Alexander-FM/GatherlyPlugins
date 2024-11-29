package com.codecorecix.gatherly.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

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

  @OneToOne
  @JoinColumn(name = "quotation_id", nullable = false)
  private Quotation quotation;

  @Column(name = "event_type", nullable = false)
  private String eventType;

  @Column(name = "event_date", nullable = false)
  private LocalDate eventDate;

  @Column(name = "location", nullable = false)
  private String location;

  @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
  private Schedule schedule;
}

