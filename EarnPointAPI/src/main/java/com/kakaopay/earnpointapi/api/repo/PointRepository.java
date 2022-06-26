package com.kakaopay.earnpointapi.api.repo;


import com.kakaopay.earnpointapi.api.vo.PointVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<PointVo, Integer> {     // <Entity, 기본키 타입>

    public List<PointVo> findAll();

    public Optional<PointVo> findById(Integer id);

    public PointVo findByBarcode(String barcode);

    public Optional<PointVo> findByBarcodeAndCategory(String barcode, Long category);


}
