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
@Table(name = "quotation")
public class Quotation implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne(mappedBy = "quotation", cascade = CascadeType.ALL)
  private Event event;

  @OneToMany
  @JoinColumn(name = "quotation_id")
  private List<Services> includedServices;

  @Column(name = "total_cost", nullable = false)
  private Double totalCost;

  @Column(name = "issue_date", nullable = false)
  private LocalDate issueDate;

  public void calculateTotalCost() {
    this.totalCost = includedServices.stream()
      .mapToDouble(Services::getCost)
      .sum();
  }
}
