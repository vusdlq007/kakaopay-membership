package com.kakaopay.earnpointapi.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//@AllArgsConstructor
@Data
public class EarnRequestDTO {

    @ApiModelProperty(value = "요청 타입", dataType = "string")
    private String accessType;

    @ApiModelProperty(value = "요청 카테고리", required = true)
    private Integer category;

    @ApiModelProperty(value = "요청 바코드", required = true)
    private String barcode;

    @ApiModelProperty(value = "적립할 포인트", required = true)
    private Integer earnPoint;

    public EarnRequestDTO() { }

    public EarnRequestDTO(String accessType, Integer category, String barcode, Integer earnPoint) {
        this.accessType = accessType;
        this.category = category;
        this.barcode = barcode;
        this.earnPoint = earnPoint;
    }
}
