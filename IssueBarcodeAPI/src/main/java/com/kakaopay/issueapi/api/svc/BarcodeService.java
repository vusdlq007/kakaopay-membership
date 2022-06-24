package com.kakaopay.issueapi.api.svc;

import com.kakaopay.issueapi.api.dto.CoreRequestDTO;
import com.kakaopay.issueapi.api.dto.CoreResponseDTO;


public interface BarcodeService {

    CoreResponseDTO issueBarcode(CoreRequestDTO requestDto);

    CoreResponseDTO readBarcode(CoreRequestDTO requestDto);

    CoreResponseDTO readAllBarcode(CoreRequestDTO requestDto);

}
