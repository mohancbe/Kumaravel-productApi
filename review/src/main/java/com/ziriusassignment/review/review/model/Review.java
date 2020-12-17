package com.ziriusassignment.review.review.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.ToString;

@Entity
@ToString
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Integer rate;

  private String comment;

  @ManyToOne
  private ReviewGroup reviewGroup;

  public Review() {

  }

  public Review(Long id, Integer rate, String comment, ReviewGroup reviewGroup) {
    super();
    this.id = id;
    this.rate = rate;
    this.comment = comment;
    this.reviewGroup = reviewGroup;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getRate() {
    return rate;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public ReviewGroup getReviewGroup() {
    return reviewGroup;
  }

  public void setReviewGroup(ReviewGroup reviewGroup) {
    this.reviewGroup = reviewGroup;
  }

}
