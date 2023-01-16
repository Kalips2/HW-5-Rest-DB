package com.example.hw4restdb.util.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Exception for the case when warehouse doesn't exist.
 */
public class WarehousesNotFoundException extends ApplicationRestException {
  /**
   * Constructor for general exception.
   */
  public WarehousesNotFoundException() {
    super("Warehouse not found", HttpStatus.NOT_FOUND, "There is no Warehouse with such code!");
  }
}
