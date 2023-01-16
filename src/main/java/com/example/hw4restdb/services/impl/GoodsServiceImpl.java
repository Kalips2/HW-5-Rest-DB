package com.example.hw4restdb.services.impl;

import com.example.hw4restdb.dto.GoodsDto;
import com.example.hw4restdb.model.Goods;
import com.example.hw4restdb.model.Manufacturers;
import com.example.hw4restdb.repositories.GoodsRepo;
import com.example.hw4restdb.repositories.ManufacturersRepo;
import com.example.hw4restdb.services.GoodsService;
import com.example.hw4restdb.util.exceptions.GoodsNotFoundException;
import com.example.hw4restdb.util.exceptions.ManufacturesNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsServiceImpl implements GoodsService {
  private final GoodsRepo goodsRepo;
  private final ManufacturersRepo manufacturersRepo;

  public GoodsServiceImpl(GoodsRepo goodsRepo, ManufacturersRepo manufacturersRepo) {
    this.goodsRepo = goodsRepo;
    this.manufacturersRepo = manufacturersRepo;
  }

  @Transactional
  @Override
  public List<Goods> getAllGoods() {
    return goodsRepo.findAll();
  }

  @Transactional
  @Override
  public Goods getGoodsByCode(Long code) {
    return goodsRepo.findById(code).orElseThrow(GoodsNotFoundException::new);
  }

  @Transactional
  @Override
  public Goods createGoods(GoodsDto goodsDto) {
    Manufacturers manufacturers =
        manufacturersRepo.findById(goodsDto.getManufacturers_code()).orElseThrow(
            ManufacturesNotFoundException::new);

    Goods newGoods =
        new Goods(goodsDto.getName(), goodsDto.getPrice(), manufacturers,
            goodsDto.getManufacturers_code());

    return goodsRepo.save(newGoods);
  }

  @Transactional
  @Override
  public boolean updateGoods(GoodsDto goodsDto, Long code) {
    Manufacturers manufacturers =
        manufacturersRepo.findById(goodsDto.getManufacturers_code()).orElseThrow(
            ManufacturesNotFoundException::new);
    Goods currentGoods = goodsRepo.findById(code).orElseThrow(GoodsNotFoundException::new);

    currentGoods.setName(goodsDto.getName());
    currentGoods.setManufacturers(manufacturers);
    currentGoods.setManufacturers_code(goodsDto.getManufacturers_code());
    currentGoods.setPrice(goodsDto.getPrice());

    goodsRepo.save(currentGoods);
    return true;
  }

  @Transactional
  @Override
  public boolean deleteGoods(Long code) {
    if (goodsRepo.existsById(code)) {
      goodsRepo.deleteById(code);
      return true;
    }
    throw new GoodsNotFoundException();
  }
}
