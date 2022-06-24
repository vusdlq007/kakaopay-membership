package com.kakaopay.usepointapi.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

//@AllArgsConstructor
@Data
public class UseResponseDTO {

    @ApiModelProperty(value = "응답 코드", dataType = "integer", example = "200")
    private Integer resCode;
    @ApiModelProperty(value = "응답 메시지", dataType = "string")
    private String resMessage;

    @ApiModelProperty(value = "업종정보", dataType = "integer")
    private Integer category;

    @ApiModelProperty(value = "바코드", dataType = "string")
    private String barcode;

    @ApiModelProperty(value = "사용할 포인트")
    private Integer usePoint;

    @ApiModelProperty(value = "승인 시간")
    private LocalDateTime approvedAt;

    @ApiModelProperty(value = "액션 구분")
    private String accessType;

    @ApiModelProperty(value = "상점명", dataType = "string")
    private String storeName;

    public UseResponseDTO() { }

    public UseResponseDTO(Integer resCode, String resMessage) {
        this.resCode = resCode;
        this.resMessage = resMessage;
    }

    public UseResponseDTO(Integer resCode, String resMessage, Integer category, LocalDateTime approvedAt, String accessType, String storeName, String barcode, Integer usePoint) {
        this.resCode = resCode;
        this.resMessage = resMessage;
        this.category = category;
        this.approvedAt = approvedAt;
        this.accessType = accessType;
        this.storeName = storeName;
        this.barcode = barcode;
        this.usePoint = usePoint;
    }


}
