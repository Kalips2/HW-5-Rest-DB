package com.example.hw4restdb.controllers;

import com.example.hw4restdb.model.Manufacturers;
import com.example.hw4restdb.services.impl.ManufacturersServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Manufacturers controller.
 */
@RestController
@RequestMapping("/manufacturers")
public class ManufacturersController {
  private final ManufacturersServiceImpl manufacturersService;

  /**
   * Autowired constructor for Manufacturers controller.
   *
   * @param manufacturersService - ManufacturersService that we need to get access to business logic.
   */
  public ManufacturersController(ManufacturersServiceImpl manufacturersService) {
    this.manufacturersService = manufacturersService;
  }

  /**
   * Method for get all manufacturers.
   *
   * @return list of manufacturers(code, name, address, list of goods that they produce with information about each) with response status OK.
   */
  @PostMapping("/getAll")
  public ResponseEntity<List<Manufacturers>> getAllGoods() {
    return ResponseEntity.ok(manufacturersService.getAllManufacturers());
  }

  /**
   * Method for get information about a manufacturer by its code.
   *
   * @return instance of goods(code, name, address, list of goods that they produce with information about each) with response status OK.
   */
  @PostMapping("/{code}")
  public ResponseEntity<Manufacturers> getGoodsById(@PathVariable @Valid Long code) {
    return new ResponseEntity<>(manufacturersService.getManufacturersByCode(code), HttpStatus.OK);
  }

}
