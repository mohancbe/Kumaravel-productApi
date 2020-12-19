package com.ziriusassignment.product.service.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziriusassignment.product.dto.ProductDto;
import com.ziriusassignment.product.dto.ReviewDto;
import com.ziriusassignment.product.dto.ReviewGroupDto;
import com.ziriusassignment.product.dto.mapper.ProductMapper;
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

  @Override
  public ProductDto addProduct(ProductRequest productRequest) {
    ReviewGroupDto reviewGroupDto = productReviewService.addReviewGroup(request.getHeader("Authorization"),
        "Creating review group for product");
    Product product = ProductMapper.toProduct(productRequest);
    product.setReviewGroup(reviewGroupDto.getId());
    return ProductMapper.toProductDto(productRepository.save(product), new ReviewGroupDto());
  }

  @Override
  public ProductDto updateProduct(ProductRequest productRequest) {
    return ProductMapper.toProductDto(productRepository.save(ProductMapper.toProduct(productRequest)),
        new ReviewGroupDto());
  }

  @Override
  public ProductDto getProduct(Long productId) {
    Optional<Product> productOptional = productRepository.findById(productId);
    if (productOptional.isEmpty()) {
      throw new ProductNotFoundException("Product id: " + productId + " is not found");
    }
    Product product = productOptional.get();
    ReviewGroupDto reviewGroup = productReviewService.getAverageRating(product.getReviewGroup());
    ReviewResponse reviews = productReviewService.getReviews(product.getReviewGroup(), 0, 5, "");
    reviewGroup.setReview(reviews);

    return ProductMapper.toProductDto(product, reviewGroup);
  }

  @Override
  public ReviewDto addReview(Long productId, ReviewRequest reviewRequest) {
    Optional<Product> product = productRepository.findById(productId);
    if (product.isEmpty()) {
      throw new EntityNotFoundException("Product id: " + productId + " is not found");
    }
    return productReviewService.addReview(request.getHeader("Authorization"), product.get().getReviewGroup(),
        reviewRequest);
  }

  @Override
  public ReviewResponse getReviews(Long productId, Integer page, Integer size, String sortBy) {
    Optional<Product> productOptional = productRepository.findById(productId);
    if (productOptional.isEmpty()) {
      throw new ProductNotFoundException("Product id: " + productId + " is not found");
    }
    return productReviewService.getReviews(productOptional.get().getReviewGroup(), page, size, sortBy);
  }

}
