package com.ziriusassignment.product.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziriusassignment.product.dto.ProductDto;
import com.ziriusassignment.product.dto.ReviewDto;
import com.ziriusassignment.product.dto.ReviewGroupDto;
import com.ziriusassignment.product.dto.mapper.ProductMapper;
import com.ziriusassignment.product.dto.request.ProductPatchRequest;
import com.ziriusassignment.product.dto.request.ProductRequest;
import com.ziriusassignment.product.dto.request.ReviewRequest;
import com.ziriusassignment.product.dto.response.ReviewResponse;
import com.ziriusassignment.product.exception.ProductNotFoundException;
import com.ziriusassignment.product.model.Product;
import com.ziriusassignment.product.repository.ProductRepository;
import com.ziriusassignment.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ProductReviewService productReviewService;

  @Autowired
  HttpServletRequest request;

  private static final String PRODUCT_NOT_FOUND_MESSAGE = "Product id is not found : ";
  
  @Override
  public ProductDto addProduct(ProductRequest productRequest) {
    ReviewGroupDto reviewGroupDto = productReviewService.addReviewGroup(request.getHeader("Authorization"),
        "Creating review group for product");
    Product product = ProductMapper.toProduct(productRequest);
    product.setReviewGroup(reviewGroupDto.getId());
    return ProductMapper.toProductDto(productRepository.save(product), new ReviewGroupDto());
  }

  @Override
  public ProductDto updateProduct(Long productId, ProductPatchRequest productRequest) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE + productId));
    return ProductMapper.toProductDto(productRepository.save(ProductMapper.patchProduct(product, productRequest)),
        new ReviewGroupDto());
  }

  @Override
  public ProductDto getProduct(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE + productId));
    ReviewGroupDto reviewGroup = productReviewService.getAverageRating(product.getReviewGroup());
    ReviewResponse reviews = productReviewService.getReviews(product.getReviewGroup(), 0, 5, "");
    reviewGroup.setReview(reviews);

    return ProductMapper.toProductDto(product, reviewGroup);
  }

  @Override
  public ReviewDto addReview(Long productId, ReviewRequest reviewRequest) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE + productId));
    return productReviewService.addReview(request.getHeader("Authorization"), product.getReviewGroup(), reviewRequest);
  }

  @Override
  public ReviewResponse getReviews(Long productId, Integer page, Integer size, String sortBy) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE + productId));
    return productReviewService.getReviews(product.getReviewGroup(), page, size, sortBy);
  }

}
