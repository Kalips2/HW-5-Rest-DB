package com.example.hw4restdb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Goods entity class.
 */
@Entity
@Table(name = "goods")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "code")
  private Long id;

  @NotNull
  @Column(name = "name")
  private String name;

  @NotNull
  @Column(name = "price")
  private BigDecimal price;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "manufacturers_id")
  @JsonBackReference
  private Manufacturers manufacturers;

  //manufactures Foreign Key
  @Column(name = "manufacturers_id", insertable = false, updatable = false)
  private Long manufacturers_code;

  public Goods(String name, BigDecimal price , Manufacturers manufacturers, Long manufacturers_fk) {
    this.name = name;
    this.price = price;
    this.manufacturers = manufacturers;
    this.manufacturers_code = manufacturers_fk;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Goods goods = (Goods) o;
    return Objects.equals(id, goods.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Goods{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", price=" + price +
        ", manufacturers=" + manufacturers +
        ", manufacturers_code=" + manufacturers_code +
        '}';
  }
}
