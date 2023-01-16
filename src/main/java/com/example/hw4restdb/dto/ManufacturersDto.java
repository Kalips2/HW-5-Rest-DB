package com.example.hw4restdb.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturersDto {
  @NotEmpty
  private String name;
  @NotEmpty
  private String address;
}
