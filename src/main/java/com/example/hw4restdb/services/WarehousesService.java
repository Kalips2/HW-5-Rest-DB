package com.example.hw4restdb.services;

import com.example.hw4restdb.model.Warehouses;
import java.util.List;

/**
 * Interface that describes all methods for Warehouses.
 */
public interface WarehousesService {
  List<Warehouses> getAllWarehouses();

  Warehouses getWarehousesByCode(Long code);
}
