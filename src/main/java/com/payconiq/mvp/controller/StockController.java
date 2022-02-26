package com.payconiq.mvp.controller;

import com.payconiq.mvp.model.StockRequest;
import com.payconiq.mvp.model.StockResponse;
import com.payconiq.mvp.model.UpdateStockPriceRequest;
import com.payconiq.mvp.service.StockService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class StockController {

  @Autowired StockService service;

  @GetMapping("/stocks")
  public ResponseEntity<?> getListOfStocks(
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "25") Integer pageSize,
      @RequestParam(defaultValue = "id") String sortBy) {
    log.info("Inside method getListOfStocks.");
    final var listOfStocks = service.getListOfStocks(pageNo, pageSize, sortBy);
    return new ResponseEntity<>(listOfStocks, HttpStatus.OK);
  }

  @PostMapping("/stocks")
  public ResponseEntity<?> createStock(@RequestBody @Valid final StockRequest request) {
    log.info("Inside method createStock.");
    final var stock = service.createStock(request);
    return new ResponseEntity<>(stock, HttpStatus.CREATED);
  }

  @GetMapping("/stocks/{id}")
  public ResponseEntity<?> getStockById(@PathVariable @NotNull @Size(min = 1) final Long id) {
    log.info("Inside method getStockById");
    final var stock = service.getStockById(id);
    return new ResponseEntity<>(stock, HttpStatus.OK);
  }

  @PatchMapping("/stocks/{id}")
  public ResponseEntity<?> updateStockPrice(
      @PathVariable final Long id, @RequestBody @Valid UpdateStockPriceRequest request) {
    log.info("Inside method updateStockPrice");
    final var result = service.updateStockPrice(id, request);
    final var response = new StockResponse();
    if (result == 1) {
      response.setCode(200);
      response.setMessage("Successfully updated Stock with ID : " + id);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
    response.setCode(400);
    response.setMessage("Failed to update the price of Stock with ID : " + id);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping("/stocks/{id}")
  public ResponseEntity<?> deleteStock(@PathVariable @NotNull @Size(min = 1) final Long id) {
    log.info("Inside method deleteStock");
    service.deleteStockById(id);
    final var response = new StockResponse();
    response.setCode(200);
    response.setMessage("Successfully deleted Stock with ID : " + id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
