package com.example.hw4restdb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "warehouses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Warehouses {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "code")
  private Long code;

  @NotNull
  @Column(name = "name")
  private String name;

  @NotNull
  @Column(name = "address")
  private String address;
}
