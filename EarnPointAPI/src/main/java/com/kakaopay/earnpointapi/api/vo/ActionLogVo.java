package com.kakaopay.earnpointapi.api.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "T_ACT_LOG")
public class ActionLogVo {

    @Id
    @Column(name = "id")
    private Integer logId;

//    @JsonBackReference
//    @ManyToOne         // 하나의 Member는 여러개의 log를 가질수 있기때문에 T_ACT_LOG 관점에서는 @ManyToOne이 된다. 해당 키의 주인되는 엔티티는 외래키를 가지고 있기때문에 현재 테이블인 T_ACT_LOG가 된다.
    @JoinColumn
    private String barcode;

    @JoinColumn
    private Integer category;

    @Column(name = "ac_point")
    private Integer acPoint;                // 적립이나 차감된 포인트 치수

    @Column(name = "remain_point")
    private Integer remainPoint;            // 남은 포인트

    @Column(name = "ac_type")
    private String acType;                  // 액션 타입

    @Column(name = "store_name")
    private String storeName;               // 상점명

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


}
