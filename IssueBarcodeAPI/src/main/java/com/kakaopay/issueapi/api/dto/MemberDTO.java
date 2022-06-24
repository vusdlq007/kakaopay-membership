package com.kakaopay.issueapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;


@Data
public class MemberDTO {

    @JsonProperty("member_id")
    private Integer memberId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("barcode")
    private String barcode;

    @JsonProperty("created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //데이터 포맷 변환
    private LocalDateTime createdAt;

}
