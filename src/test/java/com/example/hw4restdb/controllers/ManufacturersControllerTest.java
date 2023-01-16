package com.example.hw4restdb.controllers;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.hw4restdb.Hw4RestDbApplication;
import com.example.hw4restdb.dto.GoodsDto;
import com.example.hw4restdb.model.Manufacturers;
import com.example.hw4restdb.repositories.ManufacturersRepo;
import com.example.hw4restdb.services.impl.ManufacturersServiceImpl;
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
 * Integration test for Manufacturers controller.
 */
@SpringBootTest(classes = Hw4RestDbApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(value = {"/data-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/after-test.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ManufacturersControllerTest {

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private ManufacturersServiceImpl manufacturersService;
  @Autowired
  private ManufacturersRepo manufacturersRepo;
  @Autowired
  private MockMvc mockMvc;
  @Value("${sizeOfResultOnPage}")
  private int size;


  @Test
  public void getAllUsersReturnStatus200AndAllGoods() throws Exception {

    List<Manufacturers> allManufacturers = manufacturersRepo.findAll();

    mockMvc.perform(post("/manufacturers/getAll"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(3))
        .andExpect(jsonPath("$[*].code", contains(
            allManufacturers.stream()
                .map(Manufacturers::getCode)
                .map(Long::intValue)
                .toArray()
        )))
        .andExpect(jsonPath("$[*].name", contains(
            allManufacturers.stream()
                .map(Manufacturers::getName)
                .toArray()
        )))
        .andExpect(jsonPath("$[*].address", contains(
            allManufacturers.stream()
                .map(Manufacturers::getAddress)
                .toArray()
        )));
  }

  @Test
  public void getGoodsByCodeReturnCodeAndStatusOk() throws Exception {
    Long code = 1L;
    Manufacturers manufacturers = new Manufacturers("м. Київ, вул. Межигірська, 83", "Київхліб");

    mockMvc.perform(post("/manufacturers/{code}",code))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code", equalTo(code), Long.class))
        .andExpect(jsonPath("$.name", equalTo(manufacturers.getName())))
        .andExpect(jsonPath("$.address", equalTo(manufacturers.getAddress())));
  }
  @Test
  public void getGoodsByIncorrectCodeThrowExceptionAndReturnStatusNotFound() throws Exception {
    Long code = 800L;
    Manufacturers manufacturers = new Manufacturers("м. Київ, вул. Межигірська, 83", "Київхліб");

    mockMvc.perform(post("/manufacturers/{code}", code))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.ErrorMessage", equalTo("There is no manufacturers with such code!")));
  }

}
