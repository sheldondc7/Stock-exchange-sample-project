package com.payconiq.mvp.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateStockPriceRequest {

  @NotNull(message = "Price cannot be null or empty.")
  @DecimalMin(
      value = "0.1",
      inclusive = false,
      message = "Current Price cannot be more less than 0.1")
  @DecimalMax(
      value = "999999999",
      inclusive = false,
      message = "Current Price cannot be more than 999999999.")
  private Double price;
}
