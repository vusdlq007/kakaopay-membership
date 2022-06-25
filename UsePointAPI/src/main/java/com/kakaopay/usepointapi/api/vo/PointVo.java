package com.kakaopay.usepointapi.api.vo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "T_POINT")
public class PointVo {

    @Id
    @Column(name = "id")
    private Integer pointId;

    @JoinColumn
    private String barcode;

    @JoinColumn
    private Integer category;

    @JoinColumn
    @Column(name = "store_id")
    private String storeId;

    @Column(name = "point")
    private Integer point;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public void update(String barcode, Integer category, Integer point) {
        this.barcode = barcode;
        this.category = category;
        this.point = point;
    }

}
