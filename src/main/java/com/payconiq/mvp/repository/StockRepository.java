package com.payconiq.mvp.repository;

import com.payconiq.mvp.entity.StockEntity;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

  @Transactional
  @Modifying(clearAutomatically = true)
  @Query("update StockEntity e set e.currentPrice = :price where e.id = :id")
  int updateStockPrice(final Long id, final Double price);
}
