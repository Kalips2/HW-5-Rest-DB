package com.example.hw4restdb.controllers;

import com.example.hw4restdb.model.Holds;
import com.example.hw4restdb.services.HoldsService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Holds controller.
 */
@RestController
@RequestMapping("/hold")
public class HoldsController {
  private final HoldsService holdsService;

  /**
   * Autowired constructor for holds controller.
   *
   * @param holdsService - HoldsService that we need to get access to business logic.
   */
  public HoldsController(HoldsService holdsService) {
    this.holdsService = holdsService;
  }

  /**
   * Method for get all holds.
   *
   * @return list of holds(warehouses_code, goods_code, amount) with response status OK.
   */
  @PostMapping("/getAll")
  public ResponseEntity<List<Holds>> getAllGoods() {
    return ResponseEntity.ok(holdsService.getAllHolds());
  }

  /**
   * Method for get information about a holds by its primary key(warehouses_code, goods_code).
   *
   * @return instance of holds(warehouses_code, goods_code, amount) with response status OK.
   */
  @PostMapping()
  public ResponseEntity<Holds> getGoodsById(@RequestParam @Valid Long warehouse,
                                            @RequestParam @Valid Long goods) {
    return new ResponseEntity<>(
        holdsService.getWarehousesByWarehouseCodeAndGoodsCode(warehouse, goods),
        HttpStatus.OK);
  }

}
