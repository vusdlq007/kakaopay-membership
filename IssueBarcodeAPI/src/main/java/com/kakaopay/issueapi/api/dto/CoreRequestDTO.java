package com.kakaopay.issueapi.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//@AllArgsConstructor
@Data
public class CoreRequestDTO {

    @ApiModelProperty(value = "요청 타입", dataType = "string")
    private String accessType;

    @ApiModelProperty(value = "요청 내용", dataType = "json", required = true)
    private MemberDTO reqDetail;

    public CoreRequestDTO() { }

    public CoreRequestDTO(String accessType, MemberDTO reqDetail) {
        this.accessType = accessType;
        this.reqDetail = reqDetail;
    }
}
