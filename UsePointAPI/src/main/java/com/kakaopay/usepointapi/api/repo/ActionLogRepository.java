package com.kakaopay.usepointapi.api.repo;


import com.kakaopay.usepointapi.api.vo.ActionLogVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActionLogRepository extends JpaRepository<ActionLogVo, Integer> {     // <Entity, 기본키 타입>

    public List<ActionLogVo> findAll();

    public Optional<ActionLogVo> findById(Integer id);

    public ActionLogVo findByBarcode(String barcode);

    public Optional<ActionLogVo> findByBarcodeAndCategory(String barcode, Integer category);


}
