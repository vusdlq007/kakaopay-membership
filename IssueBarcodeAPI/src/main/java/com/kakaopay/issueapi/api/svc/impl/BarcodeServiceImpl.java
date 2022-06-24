package com.kakaopay.issueapi.api.svc.impl;

import com.kakaopay.issueapi.api.dto.CoreRequestDTO;
import com.kakaopay.issueapi.api.dto.CoreResponseDTO;
import com.kakaopay.issueapi.api.svc.BarcodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class BarcodeServiceImpl implements BarcodeService {


    @Override
    @Transactional
    public CoreResponseDTO joinMember(CoreRequestDTO requestDto) {

        return null;
    }

    @Override
    @Transactional
    public CoreResponseDTO issueBarcode(CoreRequestDTO requestDto) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public CoreResponseDTO readBarcode(CoreRequestDTO requestDto) {
        return null;
    }
}
