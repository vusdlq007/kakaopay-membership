package com.kakaopay.usepointapi.api.svc.impl;


import com.kakaopay.usepointapi.api.dto.ActionHistoryDTO;
import com.kakaopay.usepointapi.api.dto.UseResponseDTO;
import com.kakaopay.usepointapi.api.repo.ActionLogRepository;
import com.kakaopay.usepointapi.api.repo.PointRepository;
import com.kakaopay.usepointapi.api.svc.ActionLogService;
import com.kakaopay.usepointapi.api.vo.ActionLogVo;
import com.kakaopay.usepointapi.api.vo.PointVo;
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
    public UseResponseDTO createAction(UseResponseDTO responseDTO) {

        ActionLogVo logVo = new ActionLogVo();
        Optional<PointVo> pointVo = null;
        int remainPoint = 0;

        // Member 랜덤 고유키 생성(9자리)
        long uuid = Util.generateUUID(9);
        LocalDateTime curTime = LocalDateTime.now(ZoneId.of(timeZone));

        try{
            pointVo = pointRepository.findByBarcodeAndCategory(responseDTO.getBarcode(),responseDTO.getCategory());
        }catch (Exception e){
            log.error("ERRMSG ",e.getMessage());
            return new UseResponseDTO(ResponseCode.POINT_SEARCH_FAIL.getStatus(), ResponseCode.POINT_SEARCH_FAIL.getErrorCode(), responseDTO.getCategory(), null, TypeConstant.EARN, responseDTO.getStoreName(), responseDTO.getBarcode(), responseDTO.getUsePoint());
        }

        // 해당 맴버의 포인트 정보가 있을때.
        if(!pointVo.isEmpty()){
            remainPoint = pointVo.get().getPoint();

            logVo.setLogId((int)uuid);
            logVo.setBarcode(responseDTO.getBarcode());
            logVo.setAcPoint(responseDTO.getUsePoint());
            logVo.setAcType(responseDTO.getAccessType());
            logVo.setCategory(responseDTO.getCategory());
            logVo.setApprovedAt(responseDTO.getApprovedAt());
            logVo.setRemainPoint(remainPoint + responseDTO.getUsePoint());
            logVo.setStoreName(responseDTO.getStoreName());
            logVo.setCreatedAt(curTime);
        }else{
            return new UseResponseDTO(ResponseCode.POINT_SEARCH_FAIL.getStatus(), ResponseCode.POINT_SEARCH_FAIL.getErrorCode(), responseDTO.getCategory(), null, TypeConstant.EARN, responseDTO.getStoreName(), responseDTO.getBarcode(), responseDTO.getUsePoint());
        }

        try{
            logVo = actionLogRepository.save(logVo);
        }catch (Exception e){
            log.error("ERRMSG ",e.getMessage());
            return new UseResponseDTO(ResponseCode.LOG_PUSH_FAIL.getStatus(), ResponseCode.LOG_PUSH_FAIL.getErrorCode(), responseDTO.getCategory(), null, TypeConstant.EARN, responseDTO.getStoreName(), responseDTO.getBarcode(), responseDTO.getUsePoint());
        }

        return new UseResponseDTO(ResponseCode.LOG_PUSH_SUCCESS.getStatus(), ResponseCode.LOG_PUSH_SUCCESS.getErrorCode(), responseDTO.getCategory(), null, TypeConstant.EARN, responseDTO.getStoreName(), responseDTO.getBarcode(), responseDTO.getUsePoint());
    }
}
