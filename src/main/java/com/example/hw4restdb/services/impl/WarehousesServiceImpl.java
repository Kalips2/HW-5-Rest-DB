package com.example.hw4restdb.services.impl;

import com.example.hw4restdb.model.Warehouses;
import com.example.hw4restdb.repositories.WarehousesRepo;
import com.example.hw4restdb.services.WarehousesService;
import com.example.hw4restdb.util.exceptions.WarehousesNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for all logic with Warehouses.
 */
@Service
public class WarehousesServiceImpl implements WarehousesService {
  private final WarehousesRepo warehousesRepo;

  /**
   * WarehousesServiceImpl constructor.
   */
  public WarehousesServiceImpl(WarehousesRepo warehousesRepo) {
    this.warehousesRepo = warehousesRepo;
  }

  /**
   * Method for get all warehouses.
   *
   * @return list of warehouses(warehouses_code, name, address) with response status OK.
   */
  @Transactional
  @Override
  public List<Warehouses> getAllWarehouses() {
    return warehousesRepo.findAll();
  }

  /**
   * Method for get information about a warehouse by its code.
   * If goods is not found, throws WarehousesNotFoundException.
   *
   * @return instance of warehouses(warehouses_code, name, address) with response status OK.
   */
  @Transactional
  @Override
  public Warehouses getWarehousesByCode(Long code) {
    return warehousesRepo.findById(code).orElseThrow(WarehousesNotFoundException::new);
  }
}
