package com.kakaopay.earnpointapi.api.svc.impl;

import com.kakaopay.earnpointapi.api.dto.EarnRequestDTO;
import com.kakaopay.earnpointapi.api.dto.EarnResponseDTO;
import com.kakaopay.earnpointapi.api.repo.PointRepository;
import com.kakaopay.earnpointapi.api.svc.PointService;
import com.kakaopay.earnpointapi.api.vo.MemberVo;
import com.kakaopay.earnpointapi.api.vo.PointVo;
import com.kakaopay.earnpointapi.api.vo.StoreCategoryVo;
import com.kakaopay.earnpointapi.cmm.constant.ResponseCode;
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
    public EarnResponseDTO searchPoint(EarnRequestDTO requestDTO) {

        Optional<PointVo> pointVo = null;

        MemberVo memberVo = new MemberVo();
        memberVo.setBarcode(requestDTO.getBarcode());

        StoreCategoryVo categoryVo = new StoreCategoryVo();
        categoryVo.setCategoryId(requestDTO.getCategory());
        try{
            pointVo = pointRepository.findByBarcodeAndCategory(requestDTO.getBarcode(), requestDTO.getCategory());
        }catch (Exception e){
            log.error("ERRMSG ",e.getMessage());
            return new EarnResponseDTO(ResponseCode.POINT_SEARCH_FAIL.getStatus(), ResponseCode.POINT_SEARCH_FAIL.getErrorCode(), requestDTO);
        }


        return new EarnResponseDTO(ResponseCode.POINT_SEARCH_SUCCESS.getStatus(), ResponseCode.POINT_SEARCH_SUCCESS.getErrorCode(), requestDTO);
    }
}
