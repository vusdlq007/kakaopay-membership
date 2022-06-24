package com.kakaopay.usepointapi.api.svc;


import com.kakaopay.usepointapi.api.dto.UseRequestDTO;
import com.kakaopay.usepointapi.api.dto.UseResponseDTO;

public interface PointService {

    UseResponseDTO searchPoint(UseRequestDTO requestDTO);

}
