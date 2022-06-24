package com.kakaopay.earnpointapi.api.svc.impl;

import com.kakaopay.earnpointapi.api.dto.ActionHistoryDTO;
import com.kakaopay.earnpointapi.api.dto.EarnResponseDTO;
import com.kakaopay.earnpointapi.api.svc.ActionLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class ActionLogServiceImpl implements ActionLogService {


    @Override
    @Transactional
    public EarnResponseDTO createAction(EarnResponseDTO responseDTO) {

        ActionHistoryDTO actionHistoryDTO = new ActionHistoryDTO();



        return null;
    }
}
