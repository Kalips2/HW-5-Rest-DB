package com.example.hw4restdb.controllers;

import com.example.hw4restdb.dto.GoodsDto;
import com.example.hw4restdb.model.Goods;
import com.example.hw4restdb.payload.RestResponse;
import com.example.hw4restdb.services.impl.GoodsServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Goods controller.
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

  private final GoodsServiceImpl goodsService;

  /**
   * Autowired constructor for goods controller.
   *
   * @param goodsService - GoodsService that we need to get access to business logic.
   */
  @Autowired
  public GoodsController(GoodsServiceImpl goodsService) {
    this.goodsService = goodsService;
  }

  /**
   * Method for get all goods.
   *
   * @return list of goods(id, name, price, manufacturers_code) with response status OK.
   */
  @PostMapping("/getAll")
  public ResponseEntity<List<Goods>> getAllGoods() {
    return ResponseEntity.ok(goodsService.getAllGoods());
  }

  /**
   * Method for get information about a product by its code.
   *
   * @return instance of goods(id, name, price, manufacturers_code) with response status OK.
   */
  @PostMapping("/{code}")
  public ResponseEntity<Goods> getGoodsById(@PathVariable @Valid Long code) {
    return new ResponseEntity<>(goodsService.getGoodsByCode(code), HttpStatus.OK);
  }

  /**
   * Method for creation a new product.
   *
   * @param goodsDto - GoodsDto which contain information about new product that user want to create.
   * @return instance of goods(id, name, price, manufacturers_code) with response status CREATED.
   */
  @PostMapping("/create")
  public ResponseEntity<Goods> createGoods(@RequestBody @Valid GoodsDto goodsDto) {
    return new ResponseEntity<>(goodsService.createGoods(goodsDto), HttpStatus.CREATED);
  }

  /**
   * Method for updating a product by its code.
   *
   * @param goodsDto - GoodsDto which contain information about current product that user want to change.
   * @param code     - code of product that user want to change.
   * @return instance of goods(id, name, price, manufacturers_code) with response status OK and message of success.
   */
  @PostMapping("/update/{code}")
  public ResponseEntity<RestResponse> updateGoods(@RequestBody @Valid GoodsDto goodsDto,
                                                  @PathVariable @Valid Long code) {
    return new ResponseEntity<>(new RestResponse(goodsService.updateGoods(goodsDto, code)),
        HttpStatus.OK);
  }

  /**
   * Method for deleting a product.
   *
   * @param code - code of product that user want to delete.
   * @return instance of goods(id, name, price, manufacturers_code) with response status OK and message of success.
   */
  @DeleteMapping("/delete/{code}")
  public ResponseEntity<RestResponse> deleteGoods(@PathVariable @Valid Long code) {
    return new ResponseEntity<>(new RestResponse(goodsService.deleteGoods(code)), HttpStatus.OK);
  }

  /**
   * Method for get list of Goods that filtered by: they are created in a certain manufacturers and
   * contain a certain string in their name.
   *
   * @param name - first parameter of searching, the name of product have to contain this string.
   * @param code - second parameter of searching, the code of manufacturer where goods have to be created.
   * @param page - the number of page results. By default, one-page consists 3 objects.
   *             You can change this property in application.properties (sizeOfResultOnPage)
   * @return list of goods(id, name, price, manufacturers_code), that meet the conditions, with response status OK.
   */
  @PostMapping("/get")
  public ResponseEntity<List<Goods>> getGoodsByNameContainingAndManufacturersId(
      @RequestParam String name,
      @RequestParam Long code,
      @RequestParam Integer page) {
    return new ResponseEntity<>(
        goodsService.getGoodsByNameContainingAndManufacturersId(name, code, page), HttpStatus.OK);
  }
}
