package com.example.hw4restdb.services;

import com.example.hw4restdb.model.Holds;
import java.util.List;

/**
 * Interface that describes all methods for Holds.
 */
public interface HoldsService {
  List<Holds> getAllHolds();

  Holds getWarehousesByWarehouseCodeAndGoodsCode(Long warehouse_code, Long goods_code);
}
