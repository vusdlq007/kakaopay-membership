package com.kakaopay.earnpointapi.api.svc;


import com.kakaopay.earnpointapi.api.dto.EarnResponseDTO;

public interface ActionLogService {

    EarnResponseDTO createAction(EarnResponseDTO responseDTO);

}
