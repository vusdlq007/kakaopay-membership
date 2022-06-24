package com.kakaopay.earnpointapi.api.svc;


import com.kakaopay.earnpointapi.api.dto.EarnRequestDTO;
import com.kakaopay.earnpointapi.api.dto.EarnResponseDTO;

public interface PointService {

    EarnResponseDTO searchPoint(EarnRequestDTO requestDTO);

}
