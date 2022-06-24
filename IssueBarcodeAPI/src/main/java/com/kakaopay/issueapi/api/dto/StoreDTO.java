package com.kakaopay.issueapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class StoreDTO {


    @JsonProperty("store_id")
    private String storeId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //데이터 포맷 변환
    private Date createdAt;


}
