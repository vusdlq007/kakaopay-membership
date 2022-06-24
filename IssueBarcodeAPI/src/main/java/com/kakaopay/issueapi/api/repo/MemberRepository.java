package com.kakaopay.issueapi.api.repo;


import com.kakaopay.issueapi.api.vo.MemberVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberVo, Integer> {     // <Entity, 기본키 타입>

    // findBy뒤에 컬럼명
    public List<MemberVo> findAll();

    public Optional<MemberVo> findById(Integer id);

    public Optional<MemberVo> findByBarcode(String barcode);

    public List<MemberVo> findByName(String name);

    //like검색
    public List<MemberVo> findByNameLike(String name);


}
