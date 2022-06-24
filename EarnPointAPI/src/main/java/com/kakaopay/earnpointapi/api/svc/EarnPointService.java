package com.kakaopay.earnpointapi.api.svc;


import com.kakaopay.earnpointapi.api.dto.CoreRequestDTO;
import com.kakaopay.earnpointapi.api.dto.CoreResponseDTO;

public interface EarnPointService {

    CoreResponseDTO joinMember(CoreRequestDTO requestDto);

    CoreResponseDTO earnPoints(CoreRequestDTO requestDto);

    CoreResponseDTO readBarcode(CoreRequestDTO requestDto);

}
