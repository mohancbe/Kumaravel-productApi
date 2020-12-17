package com.ziriusassignment.product.dto.request;

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
  
  @ApiModelProperty(example = "5")
  private Integer rate;
  
  @ApiModelProperty(example = "100% value for money....No doubt. Don't think about it.. just order it..."
      + "simply awesome affordable 6gb phone.")
  private String comment;
}
