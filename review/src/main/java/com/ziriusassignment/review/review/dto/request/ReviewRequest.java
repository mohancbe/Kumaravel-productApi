package com.ziriusassignment.review.review.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
  
  @ApiModelProperty(notes = "User rating from 1-5 scale.", example = "5", required = true)
  @Min(value = 1, message = "Rating must be 1-5 scale")
  @Max(value = 5, message = "Rating must be 1-5 scale")
  @NotNull(message = "rate is required field")
  private Integer rate;
  
  @ApiModelProperty(example = "100% value for money....No doubt. Don't think about it.. just order it..."
      + "simply awesome affordable 6gb phone.")
  private String comment;
}
