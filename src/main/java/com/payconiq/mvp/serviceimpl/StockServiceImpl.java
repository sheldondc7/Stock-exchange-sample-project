package com.payconiq.mvp.serviceimpl;

import com.payconiq.mvp.entity.StockEntity;
import com.payconiq.mvp.exception.ResourceNotFoundException;
import com.payconiq.mvp.model.StockRequest;
import com.payconiq.mvp.model.UpdateStockPriceRequest;
import com.payconiq.mvp.repository.StockRepository;
import com.payconiq.mvp.service.StockService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockServiceImpl implements StockService {

  @Autowired StockRepository repository;

  @Override
  public List<StockRequest> getListOfStocks(
      final Integer pageNo, final Integer pageSize, final String sortBy) {

    final var paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

    final var entity = repository.findAll(paging);

    if (entity.hasContent()) {
      final List<StockRequest> listOfStocks = new ArrayList<>();
      entity.forEach(
          object -> {
            final var request = new StockRequest();
            request.setCurrentPrice(object.getCurrentPrice());
            request.setId(object.getId());
            request.setLastUpdate(object.getLastUpdate());
            request.setName(object.getName());
            listOfStocks.add(request);
          });
      return listOfStocks;
    }

    return new ArrayList<>();
  }

  @Override
  public StockRequest createStock(final StockRequest request) {

    final var entity = new StockEntity();
    entity.setCurrentPrice(request.getCurrentPrice());
    entity.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
    entity.setName(request.getName());

    repository.save(entity);

    request.setId(entity.getId());
    return request;
  }

  @Override
  public StockRequest getStockById(final Long id) {

    final var entity = repository.findById(id);

    if (!entity.isPresent()) {
      log.error("Stock doesn't exist with id : " + id);
      throw new ResourceNotFoundException(
          "The Stock which you are trying to retrieve doesn't exist.");
    }

    final var validatedEntity = entity.get();

    final var request = new StockRequest();
    request.setCurrentPrice(validatedEntity.getCurrentPrice());
    request.setId(validatedEntity.getId());
    request.setLastUpdate(validatedEntity.getLastUpdate());
    request.setName(validatedEntity.getName());

    return request;
  }

  @Override
  public int updateStockPrice(final Long id, final UpdateStockPriceRequest request) {
    var price = request.getPrice();

    final var entity = repository.findById(id);

    if (!entity.isPresent()) {
      log.error("Stock doesn't exist with id : " + id);
      throw new ResourceNotFoundException(
          "The Stock which you are trying to update doesn't exist.");
    }

    return repository.updateStockPrice(id, price);
  }

  @Override
  public void deleteStockById(final Long id) {

    final var entity = repository.findById(id);

    if (!entity.isPresent()) {
      log.error("Stock doesn't exist with id : " + id);
      throw new ResourceNotFoundException(
          "The Stock which you are trying to delete sdoesn't exist");
    }
    repository.deleteById(id);
  }
}
