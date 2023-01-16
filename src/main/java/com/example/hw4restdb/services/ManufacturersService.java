package com.example.hw4restdb.services;

import com.example.hw4restdb.dto.GoodsDto;
import com.example.hw4restdb.model.Goods;
import com.example.hw4restdb.model.Manufacturers;
import java.util.List;

/**
 * Interface that describes all methods for Manufacturers.
 */
public interface ManufacturersService {
  List<Manufacturers> getAllManufacturers();
  Manufacturers getManufacturersByCode(Long code);
}
