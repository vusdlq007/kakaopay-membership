package com.kakaopay.pointsearchapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

//@AllArgsConstructor
@Data
public class SearchRequestDTO {

    @ApiModelProperty(value = "요청 타입", dataType = "string")
    private String accessType;

    @ApiModelProperty(value = "시작 기간", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //데이터 포맷 변환
    @JsonProperty("started_at")
    private LocalDateTime startedAt;

    @ApiModelProperty(value = "종료 기간", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //데이터 포맷 변환
    @JsonProperty("ended_at")
    private LocalDateTime endedAt;

    @ApiModelProperty(value = "맴버의 바코드", required = true)
    private String barcode;

    public SearchRequestDTO() { }

    public SearchRequestDTO(String accessType, LocalDateTime startedAt, LocalDateTime endedAt, String barcode) {
        this.accessType = accessType;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.barcode = barcode;
    }
}
