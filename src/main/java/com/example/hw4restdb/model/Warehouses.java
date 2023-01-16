package com.example.hw4restdb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Warehouse entity class.
 */
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

  public Warehouses(String address, String name) {
    this.name = name;
    this.address = address;
  }

  @OneToMany(mappedBy = "warehouse_code")
  @JsonIgnore
  private List<Holds> holds = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Warehouses that = (Warehouses) o;
    return Objects.equals(code, that.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }
}
