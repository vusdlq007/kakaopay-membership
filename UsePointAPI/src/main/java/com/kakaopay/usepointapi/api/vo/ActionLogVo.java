package com.kakaopay.usepointapi.api.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "T_ACT_LOG")
public class ActionLogVo {

    @Id
    @Column(name = "id")
    private Integer logId;

    @JoinColumn
    private String barcode;

    @JoinColumn
    private Long category;

    @Column(name = "ac_point")
    private Integer acPoint;                // 적립이나 차감된 포인트 치수

    @Column(name = "remain_point")
    private Integer remainPoint;            // 남은 포인트

    @Column(name = "ac_type")
    private String acType;                  // 액션 타입

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


}
