package com.kakaopay.usepointapi.api.svc.impl;


import com.kakaopay.usepointapi.api.dto.UseRequestDTO;
import com.kakaopay.usepointapi.api.dto.UseResponseDTO;
import com.kakaopay.usepointapi.api.repo.PointRepository;
import com.kakaopay.usepointapi.api.svc.PointService;
import com.kakaopay.usepointapi.api.vo.MemberVo;
import com.kakaopay.usepointapi.api.vo.PointVo;
import com.kakaopay.usepointapi.api.vo.StoreCategoryVo;
import com.kakaopay.usepointapi.cmm.constant.ResponseCode;
import com.kakaopay.usepointapi.cmm.constant.TypeConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class PointServiceImpl implements PointService {

    @Value("${custom.service.timezone}")
    String timeZone;

    @Autowired
    PointRepository pointRepository;


    /**
     * 현재 포인트 조회.
     * @param requestDTO
     * @return
     */
    @Override
    public UseResponseDTO searchPoint(UseRequestDTO requestDTO) {

        Optional<PointVo> pointVo = null;

        MemberVo memberVo = new MemberVo();
        memberVo.setBarcode(requestDTO.getBarcode());

        StoreCategoryVo categoryVo = new StoreCategoryVo();
        categoryVo.setCategoryId(requestDTO.getCategoryId());
        try{
            pointVo = pointRepository.findByBarcodeAndCategory(requestDTO.getBarcode(), requestDTO.getCategoryId());
        }catch (Exception e){
            log.error("ERRMSG ",e.getMessage());
            return new UseResponseDTO(ResponseCode.POINT_SEARCH_FAIL.getStatus(), ResponseCode.POINT_SEARCH_FAIL.getErrorCode(), requestDTO.getCategoryId(), requestDTO.getStoreId(), null, TypeConstant.USE, requestDTO.getStoreName(), requestDTO.getBarcode(), requestDTO.getUsePoint());
        }

        return new UseResponseDTO(ResponseCode.POINT_SEARCH_SUCCESS.getStatus(), ResponseCode.POINT_SEARCH_SUCCESS.getErrorCode(), requestDTO.getUsePoint(), requestDTO.getStoreId(), null, TypeConstant.USE, requestDTO.getStoreName(), requestDTO.getBarcode(), requestDTO.getUsePoint());
    }
}
