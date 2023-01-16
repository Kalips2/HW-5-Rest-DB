package com.example.hw4restdb.controllers;

import com.example.hw4restdb.dto.GoodsDto;
import com.example.hw4restdb.model.Goods;
import com.example.hw4restdb.services.impl.GoodsServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {
  private final GoodsServiceImpl goodsService;

  public GoodsController(GoodsServiceImpl goodsService) {
    this.goodsService = goodsService;
  }

  @PostMapping("/getAll")
  public ResponseEntity<List<Goods>> getAllGoods() {
    return ResponseEntity.ok(goodsService.getAllGoods());
  }

  @PostMapping("/{code}")
  public ResponseEntity<Goods> getGoodsById(@PathVariable @Valid Long code) {
    return new ResponseEntity<>(goodsService.getGoodsByCode(code), HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<Goods> createGoods(@RequestBody @Valid GoodsDto goodsDto) {
    return new ResponseEntity<>(goodsService.createGoods(goodsDto), HttpStatus.CREATED);
  }

  @PostMapping("/update/{code}")
  public ResponseEntity<Boolean> updateGoods(@RequestBody @Valid GoodsDto goodsDto,
                                             @PathVariable @Valid Long code) {
    return new ResponseEntity<>(goodsService.updateGoods(goodsDto, code), HttpStatus.OK);
  }

  @DeleteMapping("/delete/{code}")
  public ResponseEntity<Boolean> deleteGoods(@PathVariable @Valid Long code) {
    return new ResponseEntity<>(goodsService.deleteGoods(code), HttpStatus.OK);
  }
}
