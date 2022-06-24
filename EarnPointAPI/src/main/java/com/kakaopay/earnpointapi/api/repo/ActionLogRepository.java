package com.kakaopay.earnpointapi.api.repo;


import com.kakaopay.earnpointapi.api.vo.ActionLogVo;
import com.kakaopay.earnpointapi.api.vo.PointVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActionLogRepository extends JpaRepository<ActionLogVo, Integer> {     // <Entity, 기본키 타입>

    public List<ActionLogVo> findAll();

    public Optional<ActionLogVo> findById(Integer id);

    public ActionLogVo findByBarcode(String barcode);

    public Optional<ActionLogVo> findByBarcodeAndCategory(String barcode, Integer category);


}
