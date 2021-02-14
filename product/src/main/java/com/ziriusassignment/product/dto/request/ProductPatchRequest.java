package com.ziriusassignment.product.dto.request;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Min;

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
public class ProductPatchRequest {

  @ApiModelProperty(example = "POCO M2 (Pitch-Black, 64 GB)  (6-GB RAM)", required = true)
  private String name;

  @ApiModelProperty(example = "9998", required = true)
  @Min(value = 0, message = "price cannot be negative")
  private BigDecimal price;

  @ApiModelProperty(example = "12998", required = false)
  @Min(value = 0, message = "orignalPrice cannot be negative")
  private BigDecimal orignalPrice;

  @ApiModelProperty(notes = "Product images. first image will be primary image and remaining "
      + "are alternative images")
  private List<String> images;

  @ApiModelProperty(example = "INR-Indian", required = true)
  private String currency;

  @ApiModelProperty(example = "Style, performance, and innovation - the Poco M2 brings together "
      + "all these aspects with its 16.58 cm (6.53) FHD+ 1080p Full Screen Display, MediaTek Helio "
      + "G80 Octa-core processor, a quad-camera setup, and an 8 MP selfie camera. ", required = true)
  private String description;

  @ApiModelProperty(example = "Mobile", required = true)
  private String type;

  @ApiModelProperty(example = "2 Year for Handset, 1 Year for Accessories", required = false)
  private String warranty;

}
