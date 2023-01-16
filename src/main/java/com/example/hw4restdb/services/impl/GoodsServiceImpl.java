package com.example.hw4restdb.services.impl;

import com.example.hw4restdb.dto.GoodsDto;
import com.example.hw4restdb.model.Goods;
import com.example.hw4restdb.model.Manufacturers;
import com.example.hw4restdb.repositories.GoodsRepo;
import com.example.hw4restdb.repositories.ManufacturersRepo;
import com.example.hw4restdb.services.GoodsService;
import com.example.hw4restdb.util.exceptions.GoodsNotFoundException;
import com.example.hw4restdb.util.exceptions.ManufacturesNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for all logic with Goods.
 */
@Service
public class GoodsServiceImpl implements GoodsService {
  private final GoodsRepo goodsRepo;
  private final ManufacturersRepo manufacturersRepo;

  //The number of object in the one page in the Pagination.
  @Value("${sizeOfResultOnPage}")
  private int size;

  /**
   * GoodsServiceImpl constructor.
   */
  public GoodsServiceImpl(GoodsRepo goodsRepo, ManufacturersRepo manufacturersRepo) {
    this.goodsRepo = goodsRepo;
    this.manufacturersRepo = manufacturersRepo;
  }

  /**
   * Method for get all goods.
   *
   * @return list of goods(id, name, price, manufacturers_code) with response status OK.
   */
  @Transactional
  @Override
  public List<Goods> getAllGoods() {
    return goodsRepo.findAll();
  }

  /**
   * Method for get information about a product by its code.
   * If goods is not found, throws GoodsNotFoundException.
   *
   * @return instance of goods(id, name, price, manufacturers_code) with response status OK.
   */
  @Transactional
  @Override
  public Goods getGoodsByCode(Long code) {
    return goodsRepo.findById(code).orElseThrow(GoodsNotFoundException::new);
  }

  /**
   * Method for creation a new product. If manufacturer with code in goodsDto doesn't exist,
   * throws ManufacturesNotFoundException.
   *
   * @param goodsDto - GoodsDto which contain information about new product that user want to create.
   * @return instance of goods(id, name, price, manufacturers_code) with response status CREATED.
   */
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

  /**
   * Method for updating a product by its code. If manufacturer with code in goodsDto doesn't exist,
   * throws ManufacturesNotFoundException. If goods is not found (so can't update), throws GoodsNotFoundException.
   *
   * @param goodsDto - GoodsDto which contain information about current product that user want to change.
   * @param code     - code of product that user want to change.
   * @return instance of goods(id, name, price, manufacturers_code) with response status OK.
   */
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
    goodsRepo.save(currentGoods);
    return true;
  }

  /**
   * Method for deleting a product.If goods is not found (so can't delete), throws GoodsNotFoundException.
   *
   * @param code - code of product that user want to delete.
   * @return instance of goods(id, name, price, manufacturers_code) with response status OK.
   */
  @Transactional
  @Override
  public boolean deleteGoods(Long code) {
    if (goodsRepo.existsById(code)) {
      goodsRepo.deleteById(code);
      return true;
    }
    throw new GoodsNotFoundException();
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
  @Transactional
  @Override
  public List<Goods> getGoodsByNameContainingAndManufacturersId(@Valid String name,
                                                                @Valid Long code,
                                                                Integer page) {
    if (manufacturersRepo.existsById(code)) {
      return goodsRepo.findByNameContainsAndManufacturers_code(name, code,
          PageRequest.of(page, size));
    }
    throw new ManufacturesNotFoundException();
  }

}
