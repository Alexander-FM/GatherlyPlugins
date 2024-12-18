package com.codecorecix.gatherly.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service")
public class Services implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "service_name", nullable = false)
  private String serviceName;

  @Column(name = "cost", nullable = false)
  private Double cost;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "availability", nullable = false)
  private Boolean availability;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  @ManyToOne
  @JoinColumn(name = "supplier_id", nullable = false)
  private Supplier supplier;

  public void reduceQuantity(int usedQuantity) {
    if (usedQuantity > this.quantity) {
      throw new IllegalArgumentException("Insufficient quantity available.");
    }
    this.quantity -= usedQuantity;
    this.availability = this.quantity > 0;
  }
}
