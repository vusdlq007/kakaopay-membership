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

//    @JsonBackReference
//    @OneToOne(mappedBy = "point")                              // 하나의 Member는 한개의 Point를 가질수 있기때문에 @OneToOne이 된다. 해당 키의 주인되는 엔티티는 외래키를 가지고 있기때문에 현재 테이블인 T_POINT가 된다.
    @JoinColumn
    private String barcode;

//    @JsonBackReference
//    @ManyToOne                                  // 하나의 Category는 여러개의 store을 가질수 있기때문에 T_STORE 관점에서는 @ManyToOne이 된다. 해당 키의 주인되는 엔티티는 외래키를 가지고 있기때문에 현재 테이블인 T_STORE가 된다.
    @JoinColumn                                // 실제 외래키의 명을 제대로 넣어줘야한다.
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
