package com.kakaopay.earnpointapi.api.svc.impl;

import com.kakaopay.earnpointapi.api.dto.CoreRequestDTO;
import com.kakaopay.earnpointapi.api.dto.CoreResponseDTO;
import com.kakaopay.earnpointapi.api.svc.EarnPointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class EarnPointServiceImpl implements EarnPointService {


    @Override
    @Transactional
    public CoreResponseDTO joinMember(CoreRequestDTO requestDto) {

        return null;
    }

    @Override
    @Transactional
    public CoreResponseDTO earnPoints(CoreRequestDTO requestDto) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public CoreResponseDTO readBarcode(CoreRequestDTO requestDto) {
        return null;
    }
}
