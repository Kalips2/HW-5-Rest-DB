package com.example.hw4restdb.util.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * General exception for the application.
 * */
@Getter
@Setter
public class ApplicationRestException extends RuntimeException{

  @JsonIgnore
  protected final HttpStatus httpStatus;
  protected final String errorCode;
  protected final String errorMessage;

  /**
   * Constructor for general exception.
   * */
  public ApplicationRestException(String errorCode, HttpStatus httpStatus,String errorMessage) {
    this.httpStatus = httpStatus;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }
}
