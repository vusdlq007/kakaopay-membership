package com.kakaopay.earnpointapi.api.svc;


import com.kakaopay.earnpointapi.api.dto.EarnRequestDTO;
import com.kakaopay.earnpointapi.api.dto.EarnResponseDTO;

public interface EarnPointService {

    EarnResponseDTO earnPoints(EarnRequestDTO requestDTO);

}
