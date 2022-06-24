package com.kakaopay.earnpointapi.api.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


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
