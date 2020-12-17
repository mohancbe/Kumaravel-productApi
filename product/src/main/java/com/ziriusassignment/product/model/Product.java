package com.ziriusassignment.product.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  
  private String name;
  private BigDecimal price;
  private BigDecimal orignalPrice;
  private String currency;
  private String description;
  private String type;
  private String warranty;
  private Long reviewGroup;

}
