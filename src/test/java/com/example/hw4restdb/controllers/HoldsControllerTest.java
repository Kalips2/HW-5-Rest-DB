package com.example.hw4restdb.controllers;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.hw4restdb.Hw4RestDbApplication;
import com.example.hw4restdb.model.Holds;
import com.example.hw4restdb.model.Warehouses;
import com.example.hw4restdb.repositories.HoldsRepo;
import com.example.hw4restdb.repositories.WarehousesRepo;
import com.example.hw4restdb.services.impl.HoldsServiceImpl;
import com.example.hw4restdb.services.impl.WarehousesServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration test for Holds controller.
 */
@SpringBootTest(classes = Hw4RestDbApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(value = {"/data-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/after-test.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class HoldsControllerTest {

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private HoldsServiceImpl holdsService;
  @Autowired
  private HoldsRepo holdsRepo;
  @Autowired
  private MockMvc mockMvc;
  @Value("${sizeOfResultOnPage}")
  private int size;


  @Test
  public void getAllHoldsReturnStatus200AndAllGoods() throws Exception {

    List<Holds> allManufacturers = holdsRepo.findAll();

    mockMvc.perform(post("/hold/getAll"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(5))
        .andExpect(jsonPath("$[*].warehouse_code", contains(
            allManufacturers.stream()
                .map(Holds::getWarehouse_code)
                .map(Long::intValue)
                .toArray()
        )))
        .andExpect(jsonPath("$[*].goods_code", contains(
            allManufacturers.stream()
                .map(Holds::getGoods_code)
                .map(Long::intValue)
                .toArray()
        )))
        .andExpect(jsonPath("$[*].amount", contains(
            allManufacturers.stream()
                .map(Holds::getAmount)
                .toArray()
        )));
  }

  @Test
  public void getHoldsByCodeReturnCodeAndStatusOk() throws Exception {
    mockMvc.perform(post("/hold")
        .param("warehouse", "1")
        .param("goods", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.goods_code", equalTo(1)))
        .andExpect(jsonPath("$.warehouse_code", equalTo(1)))
        .andExpect(jsonPath("$.amount", equalTo(BigDecimal.valueOf(11)), BigDecimal.class));
  }

}
