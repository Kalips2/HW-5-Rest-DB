package com.example.hw4restdb.controllers;

import com.example.hw4restdb.model.Warehouses;
import com.example.hw4restdb.services.WarehousesService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Warehouses controller.
 */
@RestController
@RequestMapping("/warehouse")
public class WarehousesController {
  private final WarehousesService warehousesService;

  /**
   * Autowired constructor for warehouses controller.
   *
   * @param warehousesService - WarehousesService that we need to get access to business logic.
   */
  public WarehousesController(WarehousesService warehousesService) {
    this.warehousesService = warehousesService;
  }

  /**
   * Method for get all warehouses.
   *
   * @return list of warehouses(warehouses_code, name, address) with response status OK.
   */
  @PostMapping("/getAll")
  public ResponseEntity<List<Warehouses>> getAllGoods() {
    return ResponseEntity.ok(warehousesService.getAllWarehouses());
  }

  /**
   * Method for get information about a warehouse by its code.
   *
   * @return instance of warehouses(warehouses_code, name, address) with response status OK.
   */
  @PostMapping("/{code}")
  public ResponseEntity<Warehouses> getGoodsById(@PathVariable @Valid Long code) {
    return new ResponseEntity<>(warehousesService.getWarehousesByCode(code), HttpStatus.OK);
  }

}
