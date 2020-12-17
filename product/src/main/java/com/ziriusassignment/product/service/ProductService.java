package com.ziriusassignment.product.service;

import com.ziriusassignment.product.dto.ProductDto;
import com.ziriusassignment.product.dto.ReviewDto;
import com.ziriusassignment.product.dto.request.ProductRequest;
import com.ziriusassignment.product.dto.request.ReviewRequest;

public interface ProductService {

  ProductDto addProduct(ProductRequest productRequest);

  ProductDto updateProduct(ProductRequest productRequest);

  ProductDto getProduct(Long productId);

  ReviewDto addReview(Long productId, ReviewRequest productRequest);

}
