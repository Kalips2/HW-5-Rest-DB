package com.example.hw4restdb.services.impl;

import com.example.hw4restdb.model.Manufacturers;
import com.example.hw4restdb.repositories.ManufacturersRepo;
import com.example.hw4restdb.services.ManufacturersService;
import com.example.hw4restdb.util.exceptions.ManufacturesNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for all logic with manufacturers.
 */
@Service
public class ManufacturersServiceImpl implements ManufacturersService {
  private final ManufacturersRepo manufacturersRepo;

  /**
   * ManufacturersServiceImpl constructor.
   */
  public ManufacturersServiceImpl(ManufacturersRepo manufacturersRepo) {
    this.manufacturersRepo = manufacturersRepo;
  }

  /**
   * Method for get all manufacturers.
   *
   * @return list of manufacturers(code, name, address, list of goods that they produce with information about each) with response status OK.
   */
  @Transactional
  @Override
  public List<Manufacturers> getAllManufacturers() {
    return manufacturersRepo.findAll();
  }

  /**
   * Method for get information about a manufacturer by its code.
   * If manufacturer is not found, throws ManufacturesNotFoundException.
   *
   * @return instance of goods(code, name, address, list of goods that they produce with information about each) with response status OK.
   */
  @Transactional
  @Override
  public Manufacturers getManufacturersByCode(Long code) {
    return manufacturersRepo.findById(code).orElseThrow(ManufacturesNotFoundException::new);
  }
}
