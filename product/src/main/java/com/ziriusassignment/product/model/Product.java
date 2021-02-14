package com.ziriusassignment.product.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Min;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

  @Min(value = 0, message = "price cannot be negative")
  private BigDecimal price;

  @Min(value = 0, message = "orignalPrice cannot be negative")
  private BigDecimal orignalPrice;

  @ElementCollection
  @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
  private List<String> images;

  private String currency;

  private String description;

  private String type;

  private String warranty;

  private Long reviewGroup;

  @UpdateTimestamp
  private Timestamp modifiedDate;

  @CreationTimestamp
  private Timestamp createdDate;

}
