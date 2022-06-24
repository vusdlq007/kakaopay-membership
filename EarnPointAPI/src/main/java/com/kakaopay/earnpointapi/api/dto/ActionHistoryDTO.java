package com.kakaopay.earnpointapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ActionHistoryDTO {           // Vo는 read-only속성을 가지며 entity와 1:1성질은 가진다.

    @JsonProperty("act_hist_id")
    private String actHistId;

    @JsonProperty("store_id")
    private String storeId;

    @JsonProperty("member_barcode")
    private String memberBarcode;

    @JsonProperty("ac_Type")
    private int acType;

    @JsonProperty("remain_point")
    private int remainPoint;

    @JsonProperty("ac_point")
    private int acPoint;

    @JsonProperty("approved_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //데이터 포맷 변환
    private LocalDateTime approvedAt;

    @JsonProperty("created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //데이터 포맷 변환
    private LocalDateTime createdAt;

    @JsonProperty("create_time_greater_than")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //데이터 포맷 변환
    private LocalDateTime createTimeGreaterThan;

    @JsonProperty("create_time_less_than")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //데이터 포맷 변환
    private LocalDateTime createTimeLessThan;

    @JsonProperty("create_time_greater_than_equal")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //데이터 포맷 변환
    private LocalDateTime createTimeGreaterThanEqual;

    @JsonProperty("create_time_less_than_equal")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //데이터 포맷 변환
    private LocalDateTime createTimeLessThanEqual;


}
