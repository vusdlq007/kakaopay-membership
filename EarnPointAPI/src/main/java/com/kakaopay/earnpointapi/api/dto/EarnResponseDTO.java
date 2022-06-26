package com.kakaopay.earnpointapi.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

//@AllArgsConstructor
@Data
public class EarnResponseDTO {

    @ApiModelProperty(value = "응답 코드", dataType = "integer", example = "200")
    private Integer resCode;
    @ApiModelProperty(value = "응답 메시지", dataType = "string")
    private String resMessage;

    @ApiModelProperty(value = "상점정보", dataType = "integer")
    private String storeId;

    @ApiModelProperty(value = "업종정보", dataType = "integer")
    private Integer categoryId;

    @ApiModelProperty(value = "바코드", dataType = "string")
    private String barcode;

    @ApiModelProperty(value = "적립할 포인트")
    private Integer earnPoint;

    @ApiModelProperty(value = "승인 시간")
    private LocalDateTime approvedAt;

    @ApiModelProperty(value = "액션 구분")
    private String accessType;

    @ApiModelProperty(value = "상점명", dataType = "string")
    private String storeName;

    public EarnResponseDTO() { }

    public EarnResponseDTO(Integer resCode, String resMessage) {
        this.resCode = resCode;
        this.resMessage = resMessage;
    }

    public EarnResponseDTO(Integer resCode, String resMessage, Integer categoryId, String storeId, LocalDateTime approvedAt, String accessType, String storeName, String barcode, Integer earnPoint) {
        this.resCode = resCode;
        this.resMessage = resMessage;
        this.categoryId = categoryId;
        this.storeId = storeId;
        this.approvedAt = approvedAt;
        this.accessType = accessType;
        this.storeName = storeName;
        this.barcode = barcode;
        this.earnPoint = earnPoint;
    }


}
