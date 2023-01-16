package com.example.hw4restdb.dto;

import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDto {
  @NotEmpty
  private String name;
  private BigDecimal price;
  private Long manufacturers_code;
}
