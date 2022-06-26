package com.kakaopay.issueapi.api.vo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "T_STORE")
public class StoreVo {

    @Id
    @Column(name = "id")
    private String storeId;

    @Column(name = "name")
    private String name;

//    @JsonBackReference
//    @ManyToOne                                  // 하나의 Category는 여러개의 store을 가질수 있기때문에 T_STORE 관점에서는 @ManyToOne이 된다. 해당 키의 주인되는 엔티티는 외래키를 가지고 있기때문에 현재 테이블인 T_STORE가 된다.
    @JoinColumn                                // 실제 외래키의 명을 제대로 넣어줘야한다.
    @Column(name = "category")
    private String categoryId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
