package com.example.hw4restdb.services;

import com.example.hw4restdb.dto.GoodsDto;
import com.example.hw4restdb.model.Goods;
import java.util.List;

/**
 * Interface that describes all methods for Goods.
 */
public interface GoodsService {
  List<Goods> getAllGoods();

  Goods getGoodsByCode(Long code);

  Goods createGoods(GoodsDto goodsDto);

  boolean updateGoods(GoodsDto goodsDto, Long code);

  boolean deleteGoods(Long code);

  List<Goods> getGoodsByNameContainingAndManufacturersId(String name, Long code, Integer page);
}
