package com.payconiq.mvp.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class StockResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private int code;
  private String message;
}
