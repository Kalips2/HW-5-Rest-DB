package com.example.hw4restdb.util.exceptions;

import org.springframework.http.HttpStatus;

public class ManufacturesNotFoundException extends ApplicationRestException{
  /**
   * Constructor for general exception.
   *
   */
  public ManufacturesNotFoundException() {
    super("Manufactures not found", HttpStatus.NOT_FOUND, "There is no manufactures with such code!");
  }
}
