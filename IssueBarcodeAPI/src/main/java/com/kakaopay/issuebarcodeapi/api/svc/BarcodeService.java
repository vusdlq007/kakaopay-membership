package com.kakaopay.issuebarcodeapi.api.svc;

import com.kakaopay.issuebarcodeapi.api.dto.CoreRequestDTO;
import com.kakaopay.issuebarcodeapi.api.dto.CoreResponseDTO;


public interface BarcodeService {

    CoreResponseDTO joinMember(CoreRequestDTO requestDto);

    CoreResponseDTO issueBarcode(CoreRequestDTO requestDto);

    CoreResponseDTO readBarcode(CoreRequestDTO requestDto);

}
