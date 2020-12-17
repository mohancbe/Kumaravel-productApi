package com.ziriusassignment.review.review.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ReviewGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @OneToMany(mappedBy = "reviewGroup")
  private List<Review> reviews;

  public ReviewGroup() {

  }

  public ReviewGroup(Long id, List<Review> reviews) {
    super();
    this.id = id;
    this.reviews = reviews;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

}
