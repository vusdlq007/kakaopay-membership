package com.kakaopay.pointsearchapi.api.dto;

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

    @JsonProperty("partner_name")
    private String partnerName;

    @JsonProperty("category_id")
    private Integer categoryId;

    @JsonProperty("member_barcode")
    private String memberBarcode;

    @JsonProperty("ac_Type")
    private String acType;

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

    public ActionHistoryDTO(LocalDateTime approvedAt, String acType, Integer categoryId, String storeName){
        this.approvedAt = approvedAt;
        this.acType = acType;
        this.categoryId = categoryId;
        this.partnerName = partnerName;
    }

    public static class ActionHistoryResponse{
        @JsonProperty("approved_at")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //데이터 포맷 변환
        private LocalDateTime approvedAt;

        @JsonProperty("ac_Type")
        private String acType;

        @JsonProperty("category_id")
        private Integer categoryId;

        @JsonProperty("partner_name")
        private String partnerName;

        public ActionHistoryResponse(LocalDateTime approvedAt, String acType, Integer categoryId, String partnerName){
            this.approvedAt = approvedAt;
            this.acType = acType;
            this.categoryId = categoryId;
            this.partnerName = partnerName;
        }
    }

}
