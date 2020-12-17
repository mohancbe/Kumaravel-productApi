package com.ziriusassignment.product.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziriusassignment.product.dto.ProductDto;
import com.ziriusassignment.product.dto.ReviewDto;
import com.ziriusassignment.product.dto.ReviewGroupDto;
import com.ziriusassignment.product.dto.mapper.ProductMapper;
import com.ziriusassignment.product.dto.request.ProductRequest;
import com.ziriusassignment.product.dto.request.ReviewRequest;
import com.ziriusassignment.product.model.Product;
import com.ziriusassignment.product.repository.ProductRepository;
import com.ziriusassignment.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  ProductRepository productRepository;
  
  @Autowired
  ProductReviewService productReviewService;
  
  @Override
  public ProductDto addProduct(ProductRequest productRequest) {
    ReviewGroupDto reviewGroupDto = productReviewService.addReviewGroup();
    Product product = ProductMapper.toProduct(productRequest);
    product.setReviewGroup(reviewGroupDto.getId());
    return ProductMapper.toProductDto(productRepository.save(product), new ReviewGroupDto());
  }

  @Override
  public ProductDto updateProduct(ProductRequest productRequest) {
    return ProductMapper.toProductDto(productRepository.save(ProductMapper.toProduct(productRequest)), new ReviewGroupDto());
  }

  @Override
  public ProductDto getProduct(Long productId) {
    Optional<Product> productOptional = productRepository.findById(productId);
    if(productOptional.isEmpty()) {
      throw new RuntimeException();
    }
    Product product = productOptional.get();
    ReviewGroupDto ratings = productReviewService.getAverageRating(product.getReviewGroup());
    
    return ProductMapper.toProductDto(product, ratings);
  }

  @Override
  public ReviewDto addReview(Long productId, ReviewRequest reviewRequest) {
    Optional<Product> product = productRepository.findById(productId);
    if(product.isEmpty()) {
      throw new RuntimeException();
    }
    return productReviewService.addReview(product.get().getReviewGroup(), reviewRequest);
  }

}
