package com.kakaopay.issueapi.api.vo;

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

    @JsonBackReference
    @OneToOne(mappedBy = "point")                              // 하나의 Member는 한개의 Point를 가질수 있기때문에 @OneToOne이 된다. 해당 키의 주인되는 엔티티는 외래키를 가지고 있기때문에 현재 테이블인 T_POINT가 된다.
    private MemberVo memberId;

    @Column(name = "point")
    private String point;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


}
