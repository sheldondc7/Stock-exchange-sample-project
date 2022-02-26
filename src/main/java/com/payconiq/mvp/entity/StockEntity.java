package com.payconiq.mvp.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_STOCK")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "STOCK_ID", updatable = false, nullable = false)
  private Long id;

  @Column(name = "NAME", nullable = false)
  @NotNull
  private String name;

  @Column(name = "CURRENT_PRICE", nullable = false)
  @NotNull
  private Double currentPrice;

  @Column(name = "LAST_UPDATE", nullable = false)
  @NotNull
  private Timestamp lastUpdate;
}
