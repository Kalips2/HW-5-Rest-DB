package com.example.hw4restdb.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response to return success or not of operations.
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse {
  private boolean success;
}
