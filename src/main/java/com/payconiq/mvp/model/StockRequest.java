package com.payconiq.mvp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockRequest implements Serializable {

  /** the default serial version UID */
  private static final long serialVersionUID = 1L;

  private Long id;

  @NotEmpty(message = "Name cannot be null or empty.")
  @NotBlank(message = "Name cannot be empty space")
  @Size(min = 1)
  @Size(max = 200, message = "Name is too long.")
  private String name;

  @NotNull(message = "Current Price cannot be null or empty.")
  @DecimalMin(
      value = "0.1",
      inclusive = false,
      message = "Current Price cannot be more less than 0.1")
  @DecimalMax(
      value = "999999999",
      inclusive = false,
      message = "Current Price cannot be more than 999999999.")
  private Double currentPrice;

  private Timestamp lastUpdate;
}
