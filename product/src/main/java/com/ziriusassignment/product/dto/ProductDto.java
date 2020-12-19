package com.ziriusassignment.product.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
  
  private Long id;
  private String name;
  private BigDecimal price;
  private BigDecimal orignalPrice;
  private String currency;
  private String description;
  private String type;
  private String warranty;
  private ReviewGroupDto reviewDetails;

}
