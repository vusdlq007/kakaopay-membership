package com.kakaopay.earnpointapi.api.svc;


import com.kakaopay.earnpointapi.api.dto.EarnRequestDTO;
import com.kakaopay.earnpointapi.api.dto.EarnResponseDTO;

public interface EarnPointService {

    EarnResponseDTO joinMember(EarnRequestDTO requestDTO);

    EarnResponseDTO earnPoints(EarnRequestDTO requestDTO);

    EarnResponseDTO readBarcode(EarnRequestDTO requestDTO);

}
