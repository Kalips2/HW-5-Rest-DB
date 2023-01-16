package com.example.hw4restdb.controllers;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.hw4restdb.Hw4RestDbApplication;
import com.example.hw4restdb.dto.GoodsDto;
import com.example.hw4restdb.model.Goods;
import com.example.hw4restdb.repositories.GoodsRepo;
import com.example.hw4restdb.services.impl.GoodsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration test for Goods controller.
 */
@SpringBootTest(classes = Hw4RestDbApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(value = {"/data-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/after-test.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class GoodsControllerTest {

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private GoodsServiceImpl goodsService;
  @Autowired
  private GoodsRepo goodsRepo;
  @Autowired
  private MockMvc mockMvc;
  @Value("${sizeOfResultOnPage}")
  private int size;


  @Test
  public void getAllGoodsReturnStatus200AndAllGoods() throws Exception {

    List<Goods> allGoods = goodsRepo.findAll();

    mockMvc.perform(post("/goods/getAll"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(11))
        .andExpect(jsonPath("$[*].id", contains(
            allGoods.stream()
                .map(Goods::getId)
                .map(Long::intValue)
                .toArray()
        )))
        .andExpect(jsonPath("$[*].name", contains(
            allGoods.stream()
                .map(Goods::getName)
                .toArray()
        )))
        .andExpect(jsonPath("$[*].price", contains(
            allGoods.stream()
                .map(Goods::getPrice)
                .map(BigDecimal::doubleValue)
                .toArray()
        )))
        .andExpect(jsonPath("$[*].manufacturers_code", contains(
            allGoods.stream()
                .map(Goods::getManufacturers_code)
                .map(Long::intValue)
                .toArray()
        )));
  }

  @Test
  public void getGoodsByCodeReturnCodeAndStatusOk() throws Exception {
    Long code = 8L;
    GoodsDto goods = new GoodsDto("Сир італійський", BigDecimal.valueOf(231.10), 2L);

    mockMvc.perform(post("/goods/" + code))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(code), Long.class))
        .andExpect(jsonPath("$.name", equalTo(goods.getName())))
        .andExpect(jsonPath("$.price", equalTo(goods.getPrice().doubleValue())))
        .andExpect(
            jsonPath("$.manufacturers_code", equalTo(goods.getManufacturers_code()), Long.class));
  }

  @Test
  public void getGoodsByIncorrectCodeThrowExceptionAndReturnStatusNotFound() throws Exception {
    Long code = 800L;
    GoodsDto goods = new GoodsDto("Сир італійський", BigDecimal.valueOf(231.10), 2L);

    mockMvc.perform(post("/goods/" + code))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.ErrorMessage", equalTo("There is no goods with such code!")));
  }

  @Test
  public void createGoodsReturnGoodsAndStatusCreated() throws Exception {
    GoodsDto goodsDto = new GoodsDto("Тестовий", BigDecimal.valueOf(99.99), 1L);

    ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
    String dtoAsJson = ow.writeValueAsString(goodsDto);

    mockMvc.perform(post("/goods/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(dtoAsJson))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", equalTo(12L), Long.class))
        .andExpect(jsonPath("$.name", equalTo(goodsDto.getName())))
        .andExpect(jsonPath("$.price", equalTo(goodsDto.getPrice().doubleValue())))
        .andExpect(jsonPath("$.manufacturers_code", equalTo(goodsDto.getManufacturers_code()),
            Long.class));
  }

  @Test
  public void createGoodsWithIncorrectManufacturersCodeThrowExceptionAndReturnStatusNotFound()
      throws Exception {
    GoodsDto goodsDto = new GoodsDto("Тестовий", BigDecimal.valueOf(99.99), 100L);

    ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
    String dtoAsJson = ow.writeValueAsString(goodsDto);

    mockMvc.perform(post("/goods/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(dtoAsJson))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.ErrorMessage", equalTo("There is no manufacturers with such code!")));
  }

  @Test
  public void updateGoodsReturnStatusOkAndMessageOfSuccessTrue() throws Exception {
    GoodsDto goodsDto = new GoodsDto("Тестовий", BigDecimal.valueOf(99.99), 2L);

    ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
    String dtoAsJson = ow.writeValueAsString(goodsDto);

    mockMvc.perform(post("/goods/update/{code}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(dtoAsJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success", equalTo("true"), String.class));
  }

  @Test
  public void updateGoodsWithIncorrectManufacturersCodeReturnStatusNotFoundAndThrowException()
      throws Exception {
    GoodsDto goodsDto = new GoodsDto("Тестовий", BigDecimal.valueOf(99.99), 200L);

    ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
    String dtoAsJson = ow.writeValueAsString(goodsDto);

    mockMvc.perform(post("/goods/update/{code}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(dtoAsJson))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.ErrorMessage", equalTo("There is no manufacturers with such code!")));
  }

  @Test
  public void updateGoodsWithIncorrectPriceInDtoThrowException()
      throws Exception {
    GoodsDto goodsDto = new GoodsDto("Тестовий", BigDecimal.valueOf(-99.99), 200L);

    ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
    String dtoAsJson = ow.writeValueAsString(goodsDto);

    mockMvc.perform(post("/goods/update/{code}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(dtoAsJson))
        .andExpect(status().isBadRequest())
        .andExpect(
            jsonPath("$.ErrorMessage", equalTo("Invalid fields: {price=must be greater than 0}")));
  }

  @Test
  public void updateGoodsWithEmptyNameInDtoThrowException()
      throws Exception {
    GoodsDto goodsDto = new GoodsDto("", BigDecimal.valueOf(99.99), 200L);

    ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
    String dtoAsJson = ow.writeValueAsString(goodsDto);

    mockMvc.perform(post("/goods/update/{code}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(dtoAsJson))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.ErrorMessage", equalTo("Invalid fields: {name=must not be empty}")));
  }

  @Test
  public void updateGoodsWithIncorrectGoodsCodeReturnStatusNotFoundAndThrowException()
      throws Exception {
    GoodsDto goodsDto = new GoodsDto("Тестовий", BigDecimal.valueOf(99.99), 2L);

    ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
    String dtoAsJson = ow.writeValueAsString(goodsDto);

    mockMvc.perform(post("/goods/update/{code}", 100L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(dtoAsJson))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.ErrorMessage", equalTo("There is no goods with such code!")));
  }

  @Test
  public void deleteGoodsWithReturnSuccess() throws Exception {

    mockMvc.perform(delete("/goods/delete/{code}", 11L))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success", equalTo("true"), String.class));
  }

  @Test
  public void deleteGoodsWithForeignKeysInHoldsReturnBadRequestAndThrowException()
      throws Exception {

    mockMvc.perform(delete("/goods/delete/{code}", 1L))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.ErrorCode", equalTo(1451), Integer.class));
  }

  @Test
  public void getGoodsWithFilterReturnStatusOkAndCorrectDataPage1() throws Exception {

    List<Goods> allGoods =
        goodsRepo.findByNameContainsAndManufacturers_code("Сир", 2L, PageRequest.of(0, size));

    mockMvc.perform(post("/goods/get")
            .param("name", "Сир")
            .param("code", "2")
            .param("page", "0"))
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[*].id", contains(
            allGoods.stream()
                .map(Goods::getId)
                .map(Long::intValue)
                .toArray()
        )))
        .andExpect(jsonPath("$[*].name", contains(
            allGoods.stream()
                .map(Goods::getName)
                .toArray()
        )))
        .andExpect(jsonPath("$[*].price", contains(
            allGoods.stream()
                .map(Goods::getPrice)
                .map(BigDecimal::doubleValue)
                .toArray()
        )))
        .andExpect(jsonPath("$[*].manufacturers_code", contains(
            allGoods.stream()
                .map(Goods::getManufacturers_code)
                .map(Long::intValue)
                .toArray()
        )));
  }

  @Test
  public void getGoodsWithFilterReturnStatusOkAndCorrectDataPage2() throws Exception {

    List<Goods> allGoods = goodsRepo.findByNameContainsAndManufacturers_code("Сир", 2L,
        PageRequest.of(1, size));

    mockMvc.perform(post("/goods/get")
            .param("name", "Сир")
            .param("code", "2")
            .param("page", "1"))
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$[*].id", contains(
            allGoods.stream()
                .map(Goods::getId)
                .map(Long::intValue)
                .toArray()
        )))
        .andExpect(jsonPath("$[*].name", contains(
            allGoods.stream()
                .map(Goods::getName)
                .toArray()
        )))
        .andExpect(jsonPath("$[*].price", contains(
            allGoods.stream()
                .map(Goods::getPrice)
                .map(BigDecimal::doubleValue)
                .toArray()
        )))
        .andExpect(jsonPath("$[*].manufacturers_code", contains(
            allGoods.stream()
                .map(Goods::getManufacturers_code)
                .map(Long::intValue)
                .toArray()
        )));
  }


}
