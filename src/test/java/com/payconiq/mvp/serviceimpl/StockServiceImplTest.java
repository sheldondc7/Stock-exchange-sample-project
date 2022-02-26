package com.payconiq.mvp.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.payconiq.mvp.entity.StockEntity;
import com.payconiq.mvp.exception.ResourceNotFoundException;
import com.payconiq.mvp.model.StockRequest;
import com.payconiq.mvp.model.UpdateStockPriceRequest;
import com.payconiq.mvp.repository.StockRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class StockServiceImplTest {
  @Mock private StockRepository stockRepository;

  @InjectMocks private StockServiceImpl stockServiceImpl;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @DisplayName("Positive test case for the method getListOfStocks.")
  public void getListOfStocksPositiveTest() {

    StockEntity entity1 = new StockEntity();
    entity1.setId(1L);

    StockEntity entity2 = new StockEntity();
    entity2.setId(2L);

    List<StockEntity> listOfEntity = new ArrayList<>();
    listOfEntity.add(entity1);
    listOfEntity.add(entity2);

    Page<StockEntity> pageEntity = new PageImpl<>(listOfEntity);

    when(stockRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(pageEntity);

    List<StockRequest> result = stockServiceImpl.getListOfStocks(1, 10, "id");

    Assertions.assertNotNull(result);
  }

  @Test
  @DisplayName("Negative test case for the method getListOfStocks.")
  public void getListOfStocksNegativeTest() {

    Page<StockEntity> pageEntity = new PageImpl<>(new ArrayList<>());

    when(stockRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(pageEntity);

    List<StockRequest> result = stockServiceImpl.getListOfStocks(1, 10, "id");

    Assertions.assertNotNull(result);
  }

  @Test
  @DisplayName("Positive test case for the method createStock.")
  public void createStockPositiveTest() {

    StockEntity entity = new StockEntity();
    entity.setId(1L);
    entity.setCurrentPrice(100.00);
    entity.setName("Sheldon");

    when(stockRepository.save(entity)).thenReturn(entity);

    StockRequest request = new StockRequest();
    request.setId(1L);

    StockRequest result = stockServiceImpl.createStock(request);

    Assertions.assertEquals(request.getId(), result.getId());
  }

  @Test
  @DisplayName("Positive test case for the method getStockById.")
  public void getStockByIdPositiveTest() {

    StockEntity entity = new StockEntity();
    entity.setId(1L);

    Optional<StockEntity> entity1 = Optional.of(entity);

    when(stockRepository.findById(1L)).thenReturn(entity1);

    StockRequest result = stockServiceImpl.getStockById(1L);

    Assertions.assertEquals(entity.getId(), result.getId());
  }

  @Test
  @DisplayName("Negative test case for the method getStockById.")
  public void getStockByIdNegativeTest() {
    assertThrows(
        ResourceNotFoundException.class,
        () -> {
          StockEntity entity = new StockEntity();
          entity.setId(1L);

          Optional<StockEntity> entity1 = Optional.empty();

          when(stockRepository.findById(1L)).thenReturn(entity1);

          StockRequest result = stockServiceImpl.getStockById(1L);
        });
  }

  @Test
  @DisplayName("Positive test case for the method updateStockPrice.")
  public void updateStockPricePositiveTest() {

    StockEntity entity = new StockEntity();
    entity.setId(1L);

    Optional<StockEntity> entity1 = Optional.of(entity);

    when(stockRepository.findById(1L)).thenReturn(entity1);
    when(stockRepository.updateStockPrice(1L, 100.00)).thenReturn(1);

    UpdateStockPriceRequest request = new UpdateStockPriceRequest();
    request.setPrice(100.00);

    int result = stockServiceImpl.updateStockPrice(1L, request);

    Assertions.assertEquals(result, 1);
  }

  @Test
  @DisplayName("Negative test case for the method updateStockPrice.")
  public void updateStockPriceNegativeTest() {
    assertThrows(
        ResourceNotFoundException.class,
        () -> {
          StockEntity entity = new StockEntity();
          entity.setId(1L);

          Optional<StockEntity> entity1 = Optional.empty();

          when(stockRepository.findById(1L)).thenReturn(entity1);
          when(stockRepository.updateStockPrice(1L, 100.00)).thenReturn(1);

          UpdateStockPriceRequest request = new UpdateStockPriceRequest();
          request.setPrice(100.00);

          int result = stockServiceImpl.updateStockPrice(1L, request);
        });
  }

  @Test
  @DisplayName("Positive test case for the method deleteStockById.")
  public void deleteStockByIdPositiveTest() {

    StockEntity entity = new StockEntity();
    entity.setId(1L);

    Optional<StockEntity> entity1 = Optional.of(entity);

    when(stockRepository.findById(1L)).thenReturn(entity1);
    doNothing().when(stockRepository).deleteById(1L);

    UpdateStockPriceRequest request = new UpdateStockPriceRequest();
    request.setPrice(100.00);

    stockServiceImpl.deleteStockById(1L);
  }

  @Test
  @DisplayName("Negative test case for the method deleteStockById.")
  public void deleteStockByIdNegativeTest() {
    assertThrows(
        ResourceNotFoundException.class,
        () -> {
          StockEntity entity = new StockEntity();
          entity.setId(1L);

          Optional<StockEntity> entity1 = Optional.empty();

          when(stockRepository.findById(1L)).thenReturn(entity1);
          doNothing().when(stockRepository).deleteById(1L);

          UpdateStockPriceRequest request = new UpdateStockPriceRequest();
          request.setPrice(100.00);

          stockServiceImpl.deleteStockById(1L);
        });
  }
}
