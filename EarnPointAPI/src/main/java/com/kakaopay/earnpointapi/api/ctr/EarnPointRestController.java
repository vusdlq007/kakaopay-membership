package com.kakaopay.earnpointapi.api.ctr;

import com.kakaopay.earnpointapi.api.dto.EarnRequestDTO;
import com.kakaopay.earnpointapi.api.dto.EarnResponseDTO;
import com.kakaopay.earnpointapi.api.svc.EarnPointService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@Api(tags = {"포인트 적립 관련 서비스 컨트롤러"})
@SwaggerDefinition(tags = {
        @Tag(name = "포인트 적립 서비스 컨트롤러", description = "포인트 적립 이용에 필요한 기능 제공")
})
@RequestMapping("/api/earn")
@RestController
public class EarnPointRestController {

    @Autowired
    private EarnPointService earnService;


    /**
     * 특정 맴버의 바코드 기준 Point 적립.
     * @param
     * @return
     */
    @ApiOperation(value="특정 바코드 기준으로 Point 적립 액션", notes="상점ID, 바코드, 적립금을 받아 처리한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/point")
    public EarnResponseDTO earnPoints(@RequestBody EarnRequestDTO requestDTO){

        return earnService.earnPoints(requestDTO);
    }

}
