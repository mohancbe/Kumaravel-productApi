package com.ziriusassignment.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziriusassignment.product.dto.ProductDto;
import com.ziriusassignment.product.dto.ReviewDto;
import com.ziriusassignment.product.dto.request.ProductRequest;
import com.ziriusassignment.product.dto.request.ReviewRequest;
import com.ziriusassignment.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping("")
  public ProductDto addProducts(@RequestBody ProductRequest productRequest) {
    return productService.addProduct(productRequest);
  }
  
  @GetMapping("/{productId}")
  public ProductDto addProducts(@PathVariable Long productId) {
    return productService.getProduct(productId);
  }

  @PatchMapping("/{productId}")
  public ProductDto updateProducts(@RequestBody ProductRequest productRequest) {
    return productService.updateProduct(productRequest);
  }

  @PostMapping("/{productId}/reviews")
  public ReviewDto addProductReview(@PathVariable Long productId
      , @RequestBody ReviewRequest productRequest) {
    return productService.addReview(productId, productRequest);
  }

}
