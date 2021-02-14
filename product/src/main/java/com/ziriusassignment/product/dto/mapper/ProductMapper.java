package com.ziriusassignment.product.dto.mapper;

import com.ziriusassignment.product.dto.ProductDto;
import com.ziriusassignment.product.dto.ReviewGroupDto;
import com.ziriusassignment.product.dto.request.ProductPatchRequest;
import com.ziriusassignment.product.dto.request.ProductRequest;
import com.ziriusassignment.product.model.Product;

public class ProductMapper {

  private ProductMapper() {
    //private constructor
  }
  
  public static ProductDto toProductDto(Product product, ReviewGroupDto ratings) {
    return new ProductDto().setId(product.getId())//
        .setName(product.getName())//
        .setOrignalPrice(product.getOrignalPrice())//
        .setImages(product.getImages())//
        .setType(product.getType())//
        .setWarranty(product.getWarranty())//
        .setReviewDetails(ratings)//
        .setPrice(product.getPrice())//
        .setCurrency(product.getCurrency())//
        .setDescription(product.getDescription())//
        .setCreatedDate(product.getCreatedDate())//
        .setModifiedDate(product.getModifiedDate());
  }

  public static Product toProduct(ProductRequest productRequest) {
    Product product = new Product();
    product.setName(productRequest.getName());
    product.setOrignalPrice(productRequest.getOrignalPrice());
    product.setImages(productRequest.getImages());
    product.setType(productRequest.getType());
    product.setWarranty(productRequest.getWarranty());
    product.setPrice(productRequest.getPrice());
    product.setCurrency(productRequest.getCurrency());
    product.setDescription(productRequest.getDescription());
    return product;
  }

  public static Product patchProduct(Product toBePatched, ProductPatchRequest onlyPatch) {
    if (onlyPatch.getName() != null) {
      toBePatched.setName(onlyPatch.getName());
    }
    if (onlyPatch.getOrignalPrice() != null) {
      toBePatched.setOrignalPrice(onlyPatch.getOrignalPrice());
    }
    if (onlyPatch.getImages() != null) {
      toBePatched.setImages(onlyPatch.getImages());
    }
    if (onlyPatch.getType() != null) {
      toBePatched.setType(onlyPatch.getType());
    }
    if (onlyPatch.getWarranty() != null) {
      toBePatched.setWarranty(onlyPatch.getWarranty());
    }
    if (onlyPatch.getPrice() != null) {
      toBePatched.setPrice(onlyPatch.getPrice());
    }
    if (onlyPatch.getCurrency() != null) {
      toBePatched.setCurrency(onlyPatch.getCurrency());
    }
    if (onlyPatch.getDescription() != null) {
      toBePatched.setDescription(onlyPatch.getDescription());
    }
    return toBePatched;
  }

}
