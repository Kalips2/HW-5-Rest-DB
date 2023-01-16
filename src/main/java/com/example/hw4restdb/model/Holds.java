package com.example.hw4restdb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
 * Holds entity class.
 */
@Entity
@Table(name = "holds")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(WarehouseGoodsPK.class)
public class Holds {
  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "warehouse_code")
  @JsonBackReference
  private Warehouses warehouse;

  @Id
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @JoinColumn(name = "goods_code")
  @JsonBackReference
  private Goods goods;

  @NotNull
  @Column(name = "amount")
  private BigDecimal amount;

  //warehouses Foreign Key
  @Column(name = "warehouse_code", insertable = false, updatable = false)
  private Long warehouse_code;

  //goods Foreign Key
  @Column(name = "goods_code", insertable = false, updatable = false)
  private Long goods_code;

  public Holds(BigDecimal amount, Long warehouse_code, Long goods_code) {
    this.amount = amount;
    this.warehouse_code = warehouse_code;
    this.goods_code = goods_code;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Holds holds = (Holds) o;
    return Objects.equals(warehouse_code, holds.warehouse_code) &&
        Objects.equals(goods_code, holds.goods_code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(warehouse_code, goods_code);
  }
}
