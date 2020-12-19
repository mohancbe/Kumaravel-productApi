package com.ziriusassignment.product.dto.request;

import java.math.BigDecimal;

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
public class ProductRequest {

  @ApiModelProperty(example = "POCO M2 (Pitch Black, 64 GB)  (6 GB RAM)", required = true)
  @NotNull(message = "name is required field")
  private String name;

  @ApiModelProperty(example = "9999", required = true)
  @Min(value = 0, message = "price cannot be negative")
  private BigDecimal price;

  @ApiModelProperty(example = "12999", required = false)
  @Min(value = 0, message = "orignalPrice cannot be negative")
  private BigDecimal orignalPrice;

  @ApiModelProperty(example = "INR", required = true)
  @NotNull(message = "currency is required field")
  private String currency;
  
  @ApiModelProperty(example = "tyle, performance, and innovation - the Poco M2 brings together "
      + "all these aspects with its 16.58 cm (6.53) FHD+ 1080p Full Screen Display, MediaTek Helio "
      + "G80 Octa-core processor, a quad-camera setup, and an 8 MP selfie camera. ", required = true)
  @NotNull(message = "description is required field")
  private String description;

  @ApiModelProperty(example = "Mobiles", required = true)
  @NotNull(message = "type is required field")
  private String type;
  @ApiModelProperty(example = "1 Year for Handset, 6 Months for Accessories", required = false)
  private String warranty;

}
