package com.example.hw4restdb.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto for Goods.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDto {
  @NotEmpty
  private String name;
  @Positive
  private BigDecimal price;
  private Long manufacturers_code;
}
