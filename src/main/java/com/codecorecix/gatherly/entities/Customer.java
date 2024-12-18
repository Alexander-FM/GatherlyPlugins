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
@Table(name = "Customer")
public class Customer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name="name", nullable = false)
  private String name;

  @Column(name="lastname", nullable = false)
  private String lastname;

  @Column(name="phone", nullable = false)
  private String phone;

  @Column(name = "email", nullable = false, length = 50, unique = true)
  private String email;

  @Column(name="address", nullable = false)
  private String address;

}
