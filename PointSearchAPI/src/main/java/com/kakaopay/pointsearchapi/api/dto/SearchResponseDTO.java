package com.kakaopay.pointsearchapi.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

//@AllArgsConstructor
@Data
public class SearchResponseDTO {

    @ApiModelProperty(value = "응답 코드", dataType = "integer", example = "200")
    private Integer resCode;
    @ApiModelProperty(value = "응답 메시지", dataType = "string")
    private String resMessage;

    @ApiModelProperty(value = "조회내역 리스트")
    private List<ActionHistoryDTO.ActionHistoryResponse> history;


    public SearchResponseDTO() { }

    public SearchResponseDTO(Integer resCode, String resMessage) {
        this.resCode = resCode;
        this.resMessage = resMessage;
    }

    public SearchResponseDTO(Integer resCode, String resMessage, List<ActionHistoryDTO.ActionHistoryResponse> history) {
        this.resCode = resCode;
        this.resMessage = resMessage;
        this.history = history;

    }


}
