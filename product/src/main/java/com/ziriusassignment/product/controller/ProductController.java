package com.ziriusassignment.product.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/products")
@Api(value = "Products", tags = "Products", description =  "Using this product section APIs, "
    + "you can create, update, and get products. Reviews also can able to add for products.")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping("")
  @PreAuthorize("hasRole('ROLE_PRODUCT')")
  @ApiOperation(value = "Using this API, you can add the product.",
  notes = "Using this API, you can add the product.", nickname = "productRequest")
  public ResponseEntity<ProductDto> addProducts(
      @ApiParam(value = "Product details", required = true)
      @Valid @RequestBody ProductRequest productRequest) {
    return new ResponseEntity<ProductDto>(productService.addProduct(productRequest), HttpStatus.CREATED);
  }

  @GetMapping("/{productId}")
  @ApiOperation(value = "Using this API, you can retrive the product.",
  notes = "Using this API, you can retrive the product.", nickname = "getProducts")
  public ProductDto getProducts(
      @ApiParam(value = "Product identifier that needs to be retrived.", required = true)
      @PathVariable Long productId) {
    return productService.getProduct(productId);
  }

  @PatchMapping("/{productId}")
  @PreAuthorize("hasRole('ROLE_PRODUCT')")
  @ApiOperation(value = "Using this API, you can update the product.",
  notes = "Using this API, you can update the product.", nickname = "updateProducts")
  public ProductDto updateProducts(
      @ApiParam(value = "Product details", required = true)
      @RequestBody ProductRequest productRequest) {
    return productService.updateProduct(productRequest);
  }

  @PostMapping("/{productId}/reviews")
  @PreAuthorize("hasRole('ROLE_USER')")
  @ApiOperation(value = "Using this API, you can add the review for the product.",
      notes = "Using this API, you can add the review for the product.", nickname = "addProductReview")
  public ReviewDto addProductReview(
      @ApiParam(value = "Product identifier", required = true)
      @PathVariable Long productId, 
      
      @ApiParam(value = "Review Request", required = true)
      @Valid @RequestBody ReviewRequest productRequest) {
    return productService.addReview(productId, productRequest);
  }

}
