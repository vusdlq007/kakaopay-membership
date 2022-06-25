package com.kakaopay.usepointapi.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//@AllArgsConstructor
@Data
public class UseRequestDTO {

    @ApiModelProperty(value = "요청 타입", dataType = "string")
    private String accessType;

    @ApiModelProperty(value = "상점 ID", required = true)
    private String storeId;

    @ApiModelProperty(value = "상점명", required = true)
    private String storeName;

    @ApiModelProperty(value = "요청 카테고리", required = true)
    private Integer category;

    @ApiModelProperty(value = "요청 바코드", required = true)
    private String barcode;

    @ApiModelProperty(value = "사용할 포인트", required = true)
    private Integer usePoint;

    public UseRequestDTO() { }

    public UseRequestDTO(String accessType, Integer category, String barcode, Integer usePoint) {
        this.accessType = accessType;
        this.category = category;
        this.barcode = barcode;
        this.usePoint = usePoint;
    }
}
