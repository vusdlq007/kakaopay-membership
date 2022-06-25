package com.kakaopay.earnpointapi.api.svc.impl;

import com.kakaopay.earnpointapi.api.dto.ActionHistoryDTO;
import com.kakaopay.earnpointapi.api.dto.EarnResponseDTO;
import com.kakaopay.earnpointapi.api.repo.ActionLogRepository;
import com.kakaopay.earnpointapi.api.repo.PointRepository;
import com.kakaopay.earnpointapi.api.svc.ActionLogService;
import com.kakaopay.earnpointapi.api.vo.ActionLogVo;
import com.kakaopay.earnpointapi.api.vo.PointVo;
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
public class ActionLogServiceImpl implements ActionLogService {

    @Value("${custom.service.timezone}")
    String timeZone;

    @Autowired
    ActionLogRepository actionLogRepository;

    @Autowired
    PointRepository pointRepository;

    /**
     * 맴버의 포인트 사용 내역을 DB에 기록한다.
     * @param responseDTO
     * @return
     */
    @Override
    @Transactional
    public EarnResponseDTO createAction(EarnResponseDTO responseDTO) {

        ActionLogVo logVo = new ActionLogVo();
        Optional<PointVo> pointVo = null;
        int remainPoint = 0;

        // Member 랜덤 고유키 생성(9자리)
        long uuid = Util.generateUUID(9);

        LocalDateTime curTime = LocalDateTime.now(ZoneId.of(timeZone));

        try{
            pointVo = pointRepository.findByBarcodeAndCategory(responseDTO.getBarcode(),responseDTO.getCategory());
        }catch (Exception e){
            e.printStackTrace();
            log.error("ERRMSG ",e.getMessage());
            return new EarnResponseDTO(ResponseCode.POINT_SEARCH_FAIL.getStatus(), ResponseCode.POINT_SEARCH_FAIL.getErrorCode(), responseDTO.getCategory(), null, TypeConstant.EARN, responseDTO.getStoreName(), responseDTO.getBarcode(), responseDTO.getEarnPoint());
        }

        // 해당 맴버의 포인트 정보가 있을때.
        if(!pointVo.isEmpty()){
            remainPoint = pointVo.get().getPoint();

            logVo.setLogId((int)uuid);
            logVo.setBarcode(responseDTO.getBarcode());
            logVo.setAcPoint(responseDTO.getEarnPoint());
            logVo.setAcType(responseDTO.getAccessType());
            logVo.setCategory(responseDTO.getCategory());
            logVo.setApprovedAt(responseDTO.getApprovedAt());
            logVo.setRemainPoint(remainPoint);
            logVo.setStoreName(responseDTO.getStoreName());
            logVo.setCreatedAt(curTime);
        }else{
            return new EarnResponseDTO(ResponseCode.POINT_SEARCH_FAIL.getStatus(), ResponseCode.POINT_SEARCH_FAIL.getErrorCode(), responseDTO.getCategory(), null, TypeConstant.EARN, responseDTO.getStoreName(), responseDTO.getBarcode(), responseDTO.getEarnPoint());
        }

        try{
            logVo = actionLogRepository.save(logVo);
        }catch (Exception e){
            e.printStackTrace();
            log.error("ERRMSG ",e.getMessage());
            return new EarnResponseDTO(ResponseCode.LOG_PUSH_FAIL.getStatus(), ResponseCode.LOG_PUSH_FAIL.getErrorCode(), responseDTO.getCategory(), null, TypeConstant.EARN, responseDTO.getStoreName(), responseDTO.getBarcode(), responseDTO.getEarnPoint());
        }

        return new EarnResponseDTO(ResponseCode.LOG_PUSH_SUCCESS.getStatus(), ResponseCode.LOG_PUSH_SUCCESS.getErrorCode(), responseDTO.getCategory(), null, TypeConstant.EARN, responseDTO.getStoreName(), responseDTO.getBarcode(), responseDTO.getEarnPoint());
    }
}
