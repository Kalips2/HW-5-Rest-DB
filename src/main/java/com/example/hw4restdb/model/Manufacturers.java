package com.example.hw4restdb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
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
 * Manufacturers entity class.
 */
@Entity
@Table(name = "manufacturers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturers {
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

  @OneToMany(mappedBy = "manufacturers", cascade = CascadeType.REMOVE, orphanRemoval = true)
  @JsonManagedReference
  private List<Goods> goods = new ArrayList<>();

  public Manufacturers(String address, String name) {
    this.name = name;
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Manufacturers that = (Manufacturers) o;
    return Objects.equals(code, that.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }
}
