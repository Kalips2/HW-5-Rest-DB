package com.example.hw4restdb.util.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Exception for the case when goods don't exist.
 * */
public class GoodsNotFoundException extends ApplicationRestException{
  /**
   * Constructor for general exception.
   *
   */
  public GoodsNotFoundException() {
    super("Goods not found", HttpStatus.NOT_FOUND, "There is no goods with such code!");
  }
}
