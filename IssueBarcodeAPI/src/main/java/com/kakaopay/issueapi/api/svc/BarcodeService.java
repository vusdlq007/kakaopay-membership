package com.kakaopay.issueapi.api.svc;

import com.kakaopay.issueapi.api.dto.CoreRequestDTO;
import com.kakaopay.issueapi.api.dto.CoreResponseDTO;


public interface BarcodeService {

    CoreResponseDTO joinMember(CoreRequestDTO requestDto);

    CoreResponseDTO issueBarcode(CoreRequestDTO requestDto);

    CoreResponseDTO readBarcode(CoreRequestDTO requestDto);

}
