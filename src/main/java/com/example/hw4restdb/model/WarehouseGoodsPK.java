package com.example.hw4restdb.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Class for describe composite key for Holds.
 * */
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseGoodsPK implements Serializable {
  protected Long warehouse_code;
  protected Long goods_code;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WarehouseGoodsPK that = (WarehouseGoodsPK) o;
    return Objects.equals(warehouse_code, that.warehouse_code) &&
        Objects.equals(goods_code, that.goods_code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(warehouse_code, goods_code);
  }
}
