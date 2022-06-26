package com.kakaopay.pointsearchapi.api.vo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "T_MEMBER")
public class MemberVo {

    @Id
    @Column(name = "id")
    private Integer memberId;

    @Column(name = "name")
    private String name;

    @Column(name = "barcode")
    private String barcode;

    @JsonManagedReference
    @OneToOne(cascade = {CascadeType.REMOVE})       // mappedBy = "pointId" 현재 테이블을 참조하고 있는 상대방 테이블의 외래키 필드(변수)명을 넣어줘야함. 해당필드는 실제로 DB에는 없음. 오로지 JPA 에서 외래키로 참조당하고 있다는 걸 명시하기위해씀.
    @JoinColumn(name = "id")
    private PointVo point;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


}
