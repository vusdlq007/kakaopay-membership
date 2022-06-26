package com.kakaopay.pointsearchapi.api.ctr;

import com.kakaopay.pointsearchapi.api.dto.SearchRequestDTO;
import com.kakaopay.pointsearchapi.api.dto.SearchResponseDTO;
import com.kakaopay.pointsearchapi.api.svc.SearchPointService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@Api(tags = {"포인트 내역 조회 관련 서비스 컨트롤러"})
@SwaggerDefinition(tags = {
        @Tag(name = "포인트 내역 조회 서비스 컨트롤러", description = "포인트 내역 조회 이용에 필요한 기능 제공")
})
@RequestMapping("/api/history")
@RestController
public class SearchPointRestController {

    @Autowired
    private SearchPointService searchService;


    /**
     * 특정 맴버의 바코드 기준 Point 사용내역 범위 조회.
     * @param
     * @return
     */
    @ApiOperation(value="특정 바코드 기준으로 Point 사용내역 범위 조회 액션", notes="시간기간, 종료기간, 바코드를 받아 처리한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/search")
    public SearchResponseDTO earnPoints(@RequestBody SearchRequestDTO requestDTO){

        return searchService.searchHistorys(requestDTO);
    }

}
