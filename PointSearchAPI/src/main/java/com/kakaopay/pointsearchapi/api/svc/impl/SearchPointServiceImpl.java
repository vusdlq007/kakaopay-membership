package com.kakaopay.pointsearchapi.api.svc.impl;


import com.kakaopay.pointsearchapi.api.dto.ActionHistoryDTO;
import com.kakaopay.pointsearchapi.api.dto.SearchRequestDTO;
import com.kakaopay.pointsearchapi.api.dto.SearchResponseDTO;
import com.kakaopay.pointsearchapi.api.repo.ActionLogRepository;
import com.kakaopay.pointsearchapi.api.repo.MemberRepository;
import com.kakaopay.pointsearchapi.api.repo.PointRepository;
import com.kakaopay.pointsearchapi.api.repo.StoreRepository;
import com.kakaopay.pointsearchapi.api.svc.SearchPointService;
import com.kakaopay.pointsearchapi.api.vo.ActionLogVo;
import com.kakaopay.pointsearchapi.api.vo.MemberVo;
import com.kakaopay.pointsearchapi.cmm.constant.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class SearchPointServiceImpl implements SearchPointService {

    @Value("${custom.service.timezone}")
    String timeZone;

    @Autowired
    ActionLogRepository actionLogRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    StoreRepository storeRepository;

    /**
     * 맴버의 포인트 사용 내역을 범위 조회한다.
     * @param requestDTO
     * @return
     */
    @Override
    @Transactional
    public SearchResponseDTO searchHistorys(SearchRequestDTO requestDTO) {

        List<ActionLogVo> actionLogVos = new ArrayList<>();
        List<ActionHistoryDTO.ActionHistoryResponse> actionHistoryDTOS = new ArrayList<>();

        Optional<MemberVo> memberVo = Optional.of(new MemberVo());
        memberVo.get().setBarcode(requestDTO.getBarcode());

        try {
            memberVo = memberRepository.findByBarcode(requestDTO.getBarcode());
        } catch (Exception e) {
            log.error("ERRMSG ", e.getMessage());
            return new SearchResponseDTO(ResponseCode.POINT_NO_MEMBER_FAIL.getStatus(), ResponseCode.POINT_NO_MEMBER_FAIL.getErrorCode(), null);
        }

        // 존재하지 않는 맴버일때 오류 리턴.
        if(memberVo.isEmpty()){
            return new SearchResponseDTO(ResponseCode.POINT_NO_MEMBER_FAIL.getStatus(), ResponseCode.POINT_NO_MEMBER_FAIL.getErrorCode(), null);
        }

        try {
            actionLogVos = actionLogRepository.findAllByBarcodeAndApprovedAtBetween(requestDTO.getBarcode(), requestDTO.getStartedAt(), requestDTO.getEndedAt());
        } catch (Exception e) {
            log.error("ERRMSG ", e.getMessage());
            return new SearchResponseDTO(ResponseCode.HISTORY_SEARCH_FAIL.getStatus(), ResponseCode.POINT_NO_MEMBER_FAIL.getErrorCode(), null);
        }

        for(ActionLogVo actioVo : actionLogVos){
            ActionHistoryDTO.ActionHistoryResponse historyDTO = new ActionHistoryDTO.ActionHistoryResponse(actioVo.getApprovedAt(), actioVo.getAcType(), actioVo.getCategory(), actioVo.getStoreName());
            actionHistoryDTOS.add(historyDTO);
        }

         return new SearchResponseDTO(ResponseCode.HISTORY_SEARCH_SUCCESS.getStatus(), ResponseCode.HISTORY_SEARCH_SUCCESS.getErrorCode(), actionHistoryDTOS);
    }


}
