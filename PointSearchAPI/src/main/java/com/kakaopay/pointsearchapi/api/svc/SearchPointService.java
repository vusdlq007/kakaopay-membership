package com.kakaopay.pointsearchapi.api.svc;

import com.kakaopay.pointsearchapi.api.dto.SearchRequestDTO;
import com.kakaopay.pointsearchapi.api.dto.SearchResponseDTO;

public interface SearchPointService {

    SearchResponseDTO searchHistorys(SearchRequestDTO requestDTO);

}
