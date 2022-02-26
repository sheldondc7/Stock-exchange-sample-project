package com.payconiq.mvp.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.payconiq.mvp.model.StockRequest;
import com.payconiq.mvp.model.StockResponse;
import com.payconiq.mvp.model.UpdateStockPriceRequest;
import com.payconiq.mvp.service.StockService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StockControllerTest {

  @Mock private StockService stockService;

  @InjectMocks private StockController stockController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @DisplayName("Positive test case for the method getListOfStocks.")
  public void getListOfStocksPositiveTest() {
    List<StockRequest> listofStocks = new ArrayList<>();

    var request1 = new StockRequest();
    request1.setCurrentPrice(100.00);
    request1.setId(1L);
    request1.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

    listofStocks.add(request1);

    ResponseEntity responseEntity =
        new ResponseEntity<List<StockRequest>>(listofStocks, HttpStatus.OK);

    when(stockService.getListOfStocks(1, 1, "id")).thenReturn(listofStocks);

    ResponseEntity<?> result = stockController.getListOfStocks(1, 1, "id");

    Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
  }

  @Test
  @DisplayName("Positive test case for the method createStock.")
  public void createStockPositiveTest() {

    var request1 = new StockRequest();
    request1.setCurrentPrice(100.00);
    request1.setId(1L);
    request1.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
    request1.setName("Sheldon");

    ResponseEntity responseEntity = new ResponseEntity<StockRequest>(request1, HttpStatus.CREATED);

    when(stockService.createStock(request1)).thenReturn(request1);

    ResponseEntity<?> result = stockController.createStock(request1);

    Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
  }

  @Test
  @DisplayName("Positive test case for the method getStockById.")
  public void getStockByIdPositiveTest() {

    var request1 = new StockRequest();
    request1.setCurrentPrice(100.00);
    request1.setId(1L);
    request1.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
    request1.setName("Sheldon");

    ResponseEntity responseEntity = new ResponseEntity<StockRequest>(request1, HttpStatus.OK);

    when(stockService.getStockById(1L)).thenReturn(request1);

    ResponseEntity<?> result = stockController.getStockById(1L);

    Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
  }

  @Test
  @DisplayName("Positive test case for the method updateStockPrice.")
  public void updateStockPricePositiveTest() {

    UpdateStockPriceRequest request = new UpdateStockPriceRequest();
    request.setPrice(100.00);

    StockResponse response = new StockResponse();
    response.setCode(200);
    response.setMessage("Success");

    ResponseEntity responseEntity = new ResponseEntity<StockResponse>(response, HttpStatus.OK);

    when(stockService.updateStockPrice(1L, request)).thenReturn(1);

    ResponseEntity<?> result = stockController.updateStockPrice(1L, request);

    Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
  }

  @Test
  @DisplayName("Negative test case for the method updateStockPrice.")
  public void updateStockPriceNegativeTest() {

    UpdateStockPriceRequest request = new UpdateStockPriceRequest();
    request.setPrice(100.00);

    StockResponse response = new StockResponse();
    response.setCode(400);
    response.setMessage("Failed");

    ResponseEntity responseEntity =
        new ResponseEntity<StockResponse>(response, HttpStatus.BAD_REQUEST);

    when(stockService.updateStockPrice(1L, request)).thenReturn(0);

    ResponseEntity<?> result = stockController.updateStockPrice(1L, request);

    Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
  }

  @Test
  @DisplayName("Positive test case for the method deleteStock.")
  public void deleteStockPositiveTest() {

    StockResponse response = new StockResponse();
    response.setCode(200);
    response.setMessage("Success");

    ResponseEntity responseEntity = new ResponseEntity<StockResponse>(response, HttpStatus.OK);

    doNothing().when(stockService).deleteStockById(1L);

    ResponseEntity<?> result = stockController.deleteStock(1L);

    Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
  }
}
