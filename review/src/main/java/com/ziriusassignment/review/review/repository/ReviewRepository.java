package com.ziriusassignment.review.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziriusassignment.review.review.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

  Page<Review> findByReviewGroupId(Long reviewGroupId, Pageable pageable);

}