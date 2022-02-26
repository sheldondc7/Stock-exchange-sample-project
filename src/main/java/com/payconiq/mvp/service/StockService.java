package com.payconiq.mvp.service;

import com.payconiq.mvp.model.StockRequest;
import com.payconiq.mvp.model.UpdateStockPriceRequest;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface StockService {

  List<StockRequest> getListOfStocks(
      final Integer pageNo, final Integer pageSize, final String sortBy);

  StockRequest createStock(final StockRequest request);

  StockRequest getStockById(final Long id);

  int updateStockPrice(final Long id, final UpdateStockPriceRequest request);

  void deleteStockById(final Long id);
}
