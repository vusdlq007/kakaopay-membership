package com.kakaopay.pointsearchapi.api.repo;


import com.kakaopay.pointsearchapi.api.vo.StoreVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreVo, String> {     // <Entity, 기본키 타입>

    public List<StoreVo> findAll();

    public Optional<StoreVo> findByStoreId(String id);

    public StoreVo findByStoreIdAndAndCategoryId(String storeId, String categoryId);

}
