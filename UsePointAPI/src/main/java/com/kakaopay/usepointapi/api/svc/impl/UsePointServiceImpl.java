package com.kakaopay.usepointapi.api.svc.impl;


import com.kakaopay.usepointapi.api.dto.UseRequestDTO;
import com.kakaopay.usepointapi.api.dto.UseResponseDTO;
import com.kakaopay.usepointapi.api.repo.PointRepository;
import com.kakaopay.usepointapi.api.svc.PointService;
import com.kakaopay.usepointapi.api.svc.UsePointService;
import com.kakaopay.usepointapi.api.vo.MemberVo;
import com.kakaopay.usepointapi.api.vo.PointVo;
import com.kakaopay.usepointapi.api.vo.StoreCategoryVo;
import com.kakaopay.usepointapi.cmm.constant.ResponseCode;
import com.kakaopay.usepointapi.cmm.constant.TypeConstant;
import com.kakaopay.usepointapi.cmm.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UsePointServiceImpl implements UsePointService {

    @Value("${custom.service.timezone}")
    String timeZone;

    @Autowired
    PointRepository pointRepository;

    /**
     * 포인트가 없다면 리턴처리 있다면 더티 체크로 업데이트
     * @param requestDTO
     * @return
     */
    @Override
    @Transactional
    public UseResponseDTO usePoints(UseRequestDTO requestDTO) {
        Optional<PointVo> pointVo = Optional.of(new PointVo());

        MemberVo memberVo = new MemberVo();
        memberVo.setBarcode(requestDTO.getBarcode());

        StoreCategoryVo categoryVo = new StoreCategoryVo();
        categoryVo.setCategoryId(requestDTO.getCategory());

        try{
            pointVo = pointRepository.findByBarcodeAndCategory(requestDTO.getBarcode(), requestDTO.getCategory());
        }catch (Exception e){
            log.error("ERRMSG ",e.getMessage());
            return new UseResponseDTO(ResponseCode.POINT_SEARCH_FAIL.getStatus(), ResponseCode.POINT_SEARCH_FAIL.getErrorCode(), requestDTO.getCategory(), null, TypeConstant.USE, requestDTO.getStoreName(), requestDTO.getBarcode(), requestDTO.getUsePoint());
        }


        // 포인트 정보가 없을때. (맴버십 바코드 정보가 T_POINT에 없을때)
        if (pointVo.isEmpty()){
            // 랜덤 고유키 생성(9자리)
            long uuid = Util.generateUUID(9);
            LocalDateTime curTime = LocalDateTime.now(ZoneId.of(timeZone));

            pointVo = Optional.of(new PointVo());
            pointVo.get().setPointId((int) uuid);
            pointVo.get().setBarcode(requestDTO.getBarcode());
            pointVo.get().setCategory(requestDTO.getCategory());
            pointVo.get().setPoint(requestDTO.getUsePoint());
            pointVo.get().setStoreId(requestDTO.getStoreId());
            pointVo.get().setCreatedAt(curTime);

            return new UseResponseDTO(ResponseCode.POINT_SEARCH_FAIL.getStatus(), ResponseCode.POINT_SEARCH_FAIL.getErrorCode(), requestDTO.getCategory(), null, TypeConstant.USE, requestDTO.getStoreName(), requestDTO.getBarcode(), requestDTO.getUsePoint());

        }else{
            int nowPoint = pointVo.get().getPoint();
            nowPoint -= requestDTO.getUsePoint();

            // 포인트가 없거나 쓸수없는 포인트 값일때.
            if(nowPoint <= 0){
                return new UseResponseDTO(ResponseCode.POINT_USE_NONE_FAIL.getStatus(), ResponseCode.POINT_USE_NONE_FAIL.getErrorCode(), requestDTO.getCategory(), null, TypeConstant.USE, requestDTO.getStoreName(), requestDTO.getBarcode(), requestDTO.getUsePoint());
            }else{
                // 현재 포인트에서 사용포인트를 뺀 값으로 업데이트
                pointVo.get().update(requestDTO.getBarcode(),requestDTO.getCategory(),nowPoint);
            }
        }

        return new UseResponseDTO(ResponseCode.POINT_USE_SUCCESS.getStatus(), ResponseCode.POINT_USE_SUCCESS.getErrorCode(), requestDTO.getCategory(), pointVo.get().getCreatedAt(), TypeConstant.USE, requestDTO.getStoreName(), requestDTO.getBarcode(), requestDTO.getUsePoint());

    }


}
