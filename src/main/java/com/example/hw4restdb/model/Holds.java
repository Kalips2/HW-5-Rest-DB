package com.example.hw4restdb.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "holds")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(WarehouseGoodsPK.class)
public class Holds {
  @Id
  @ManyToOne
  @JoinColumn(name = "warehouse_code")
  private Warehouses warehouse_code;
  @Id
  @ManyToOne
  @JoinColumn(name = "goods_code")
  private Goods goods_code;

  @NotNull
  @Column(name = "amount")
  private BigDecimal amount;

}
