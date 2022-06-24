package com.kakaopay.issueapi.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;



@Data
public class StoreCategoryDTO {

    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("name")
    private String name;

}
