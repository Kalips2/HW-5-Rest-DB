package com.example.hw4restdb.services.impl;

import com.example.hw4restdb.model.Holds;
import com.example.hw4restdb.repositories.HoldsRepo;
import com.example.hw4restdb.services.HoldsService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for all logic with Holds.
 */
@Service
public class HoldsServiceImpl implements HoldsService {
  private final HoldsRepo holdsRepo;

  /**
   * HoldsServiceImpl constructor.
   */
  public HoldsServiceImpl(HoldsRepo holdsRepo) {
    this.holdsRepo = holdsRepo;
  }

  /**
   * Method for get all holds.
   *
   * @return list of holds(warehouses_code, goods_code, amount) with response status OK.
   */
  @Transactional
  @Override
  public List<Holds> getAllHolds() {
    return holdsRepo.findAll();
  }

  /**
   * Method for get information about a holds by its primary key(warehouses_code, goods_code).
   *
   * @return instance of holds(warehouses_code, goods_code, amount) with response status OK.
   */
  @Transactional
  @Override
  public Holds getWarehousesByWarehouseCodeAndGoodsCode(Long warehouse_code, Long goods_code) {
    return holdsRepo.findByWarehouse_codeAndGoods_code(warehouse_code, goods_code);
  }
}
