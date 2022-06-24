package com.kakaopay.issuebarcodeapi.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//@AllArgsConstructor
@Data
public class CoreResponseDTO<T> {

    @ApiModelProperty(value = "응답 코드", dataType = "integer", example = "200")
    private Integer resCode;
    @ApiModelProperty(value = "응답 메시지", dataType = "string")
    private String resMessage;

    @ApiModelProperty(value = "응답 내용", dataType = "object", required = true)
    private T resResult;

    @ApiModelProperty(value = "요청 타입", dataType = "string")
    private String accessType;

    @ApiModelProperty(value = "액션 타입", dataType = "string")
    private String actionType;

    @ApiModelProperty(value = "요청자ID", dataType = "string")
    private String acUserId;

    public CoreResponseDTO() { }

    public CoreResponseDTO(Integer resCode, String resMessage, T responseResult) {
        this.resCode = resCode;
        this.resMessage = resMessage;
        this.resResult = responseResult;
    }

    public CoreResponseDTO(Integer resCode, String resMessage, T responseResult, String acUserId, String accessType) {
        this.resCode = resCode;
        this.resMessage = resMessage;
        this.resResult = responseResult;
        this.acUserId = acUserId;
        this.accessType = accessType;
    }


}
