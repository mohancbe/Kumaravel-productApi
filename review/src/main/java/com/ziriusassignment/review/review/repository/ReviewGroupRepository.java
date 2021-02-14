package com.ziriusassignment.review.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ziriusassignment.review.review.model.ReviewGroup;

@Repository
public interface ReviewGroupRepository extends JpaRepository<ReviewGroup, Long>{
  
  @Query("SELECT AVG(r.rate) FROM Review r where r.reviewGroup.id=:reviewGroupId") 
  Float getAverageRating(Long reviewGroupId);

}
