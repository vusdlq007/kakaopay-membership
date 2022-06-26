package com.kakaopay.earnpointapi.api.svc.impl;

import com.kakaopay.earnpointapi.api.dto.EarnRequestDTO;
import com.kakaopay.earnpointapi.api.dto.EarnResponseDTO;
import com.kakaopay.earnpointapi.api.repo.MemberRepository;
import com.kakaopay.earnpointapi.api.repo.PointRepository;
import com.kakaopay.earnpointapi.api.repo.StoreRepository;
import com.kakaopay.earnpointapi.api.svc.EarnPointService;
import com.kakaopay.earnpointapi.api.vo.MemberVo;
import com.kakaopay.earnpointapi.api.vo.PointVo;
import com.kakaopay.earnpointapi.api.vo.StoreCategoryVo;
import com.kakaopay.earnpointapi.api.vo.StoreVo;
import com.kakaopay.earnpointapi.cmm.constant.ResponseCode;
import com.kakaopay.earnpointapi.cmm.constant.TypeConstant;
import com.kakaopay.earnpointapi.cmm.util.Util;
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
public class EarnPointServiceImpl implements EarnPointService {

    @Value("${custom.service.timezone}")
    String timeZone;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    StoreRepository storeRepository;

    public EarnPointServiceImpl(MemberRepository memberRepository, PointRepository pointRepository, StoreRepository storeRepository) {
        this.memberRepository = memberRepository;
        this.pointRepository = pointRepository;
        this.storeRepository = storeRepository;
    }

    /**
     * 최초 적립시에는 단순 저장, 이후 적립은 더티 체크로 업데이트
     *
     * @param requestDTO
     * @return
     */
    @Override
    @Transactional
    public EarnResponseDTO earnPoints(EarnRequestDTO requestDTO) {

        Optional<PointVo> pointVo = Optional.of(new PointVo());
        Optional<StoreVo> storeVo = Optional.of(new StoreVo());

        Optional<MemberVo> memberVo = Optional.of(new MemberVo());
        memberVo.get().setBarcode(requestDTO.getBarcode());

        StoreCategoryVo categoryVo = new StoreCategoryVo();
        categoryVo.setCategoryId(requestDTO.getCategoryId());

        try {
             memberVo = memberRepository.findByBarcode(requestDTO.getBarcode());
        } catch (Exception e) {
            log.error("ERRMSG ", e.getMessage());
            return new EarnResponseDTO(ResponseCode.POINT_NO_MEMBER_FAIL.getStatus(), ResponseCode.POINT_NO_MEMBER_FAIL.getErrorCode(),requestDTO.getCategoryId(), requestDTO.getStoreId(), null, TypeConstant.EARN, requestDTO.getStoreName(), requestDTO.getBarcode(), requestDTO.getEarnPoint());
        }

        //없는 맴버일때 오류 리턴.
        if(memberVo.isEmpty()){
            return new EarnResponseDTO(ResponseCode.POINT_NO_MEMBER_FAIL.getStatus(), ResponseCode.POINT_NO_MEMBER_FAIL.getErrorCode(), requestDTO.getCategoryId(), requestDTO.getStoreId(), null, TypeConstant.EARN, requestDTO.getStoreName(), requestDTO.getBarcode(), requestDTO.getEarnPoint());
        }

        try {
            pointVo = pointRepository.findByBarcodeAndCategory(requestDTO.getBarcode(), requestDTO.getCategoryId());
        } catch (Exception e) {
            log.error("ERRMSG ", e.getMessage());
            return new EarnResponseDTO(ResponseCode.POINT_SEARCH_FAIL.getStatus(), ResponseCode.POINT_SEARCH_FAIL.getErrorCode(), requestDTO.getCategoryId(), requestDTO.getStoreId(), null, TypeConstant.EARN, requestDTO.getStoreName(), requestDTO.getBarcode(), requestDTO.getEarnPoint());
        }

        try {
            storeVo = storeRepository.findByStoreIdAndCategoryId(requestDTO.getStoreId(), requestDTO.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ERRMSG ", e.getMessage());
            return new EarnResponseDTO(ResponseCode.POINT_NO_STORE_FAIL.getStatus(), ResponseCode.POINT_NO_STORE_FAIL.getErrorCode(), requestDTO.getCategoryId(), requestDTO.getStoreId(), null, TypeConstant.EARN, requestDTO.getStoreName(), requestDTO.getBarcode(), requestDTO.getEarnPoint());
        }

        // 상점 정보가 없을때.
        if (storeVo.isEmpty()) {
            return new EarnResponseDTO(ResponseCode.POINT_NO_STORE_FAIL.getStatus(), ResponseCode.POINT_NO_STORE_FAIL.getErrorCode(), requestDTO.getCategoryId(), requestDTO.getStoreId(), null, TypeConstant.EARN, requestDTO.getStoreName(), requestDTO.getBarcode(), requestDTO.getEarnPoint());
        } else if (pointVo.isEmpty()) {         // 최초 적립일떄.
            // 랜덤 고유키 생성(9자리)
            long uuid = Util.generateUUID(9);

            LocalDateTime curTime = LocalDateTime.now(ZoneId.of(timeZone));

            pointVo = Optional.of(new PointVo());
            pointVo.get().setPointId((int) uuid);
            pointVo.get().setBarcode(requestDTO.getBarcode());
            pointVo.get().setCategory(requestDTO.getCategoryId());
            pointVo.get().setPoint(requestDTO.getEarnPoint());
            pointVo.get().setStoreId(requestDTO.getStoreId());
            pointVo.get().setCreatedAt(curTime);
            try {
                pointRepository.save(pointVo.get());
            } catch (Exception e) {
                log.error("ERRMSG ", e.getMessage());
                return new EarnResponseDTO(ResponseCode.POINT_EARN_FAIL.getStatus(), ResponseCode.POINT_EARN_FAIL.getErrorCode(), requestDTO.getCategoryId(), requestDTO.getStoreId(), pointVo.get().getCreatedAt(), TypeConstant.EARN, requestDTO.getStoreName(), requestDTO.getBarcode(), requestDTO.getEarnPoint());
            }

        } else {
            int nowPoint = pointVo.get().getPoint();
            // 현재 포인트에서 적립포인트를 더한 값으로 업데이트
            pointVo.get().update(requestDTO.getBarcode(), requestDTO.getCategoryId(), nowPoint + requestDTO.getEarnPoint(), requestDTO.getStoreId());
        }

        return new EarnResponseDTO(ResponseCode.POINT_EARN_SUCCESS.getStatus(), ResponseCode.POINT_EARN_SUCCESS.getErrorCode(), requestDTO.getCategoryId(), requestDTO.getStoreId(), pointVo.get().getCreatedAt(), TypeConstant.EARN, requestDTO.getStoreName(), requestDTO.getBarcode(), requestDTO.getEarnPoint());
    }

}
