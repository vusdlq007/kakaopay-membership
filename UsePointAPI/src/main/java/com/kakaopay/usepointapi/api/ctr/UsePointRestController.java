package com.kakaopay.usepointapi.api.ctr;

import com.kakaopay.usepointapi.api.dto.UseRequestDTO;
import com.kakaopay.usepointapi.api.dto.UseResponseDTO;
import com.kakaopay.usepointapi.api.svc.UsePointService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@Api(tags = {"포인트 사용 관련 서비스 컨트롤러"})
@SwaggerDefinition(tags = {
        @Tag(name = "포인트 사용 서비스 컨트롤러", description = "포인트 사용 이용에 필요한 기능 제공")
})
@RequestMapping("/api/use")
@RestController
public class UsePointRestController {

    @Autowired
    private UsePointService usePointService;

    @ApiOperation(value="API 동작여부 확인", notes="실행되고있는 현재 모듈 폴링체크 기능으로 요청헤더 응답.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/healthCheck")
    public String healthCheck(){

        return "호출성공! ";
    }



    /**
     * 특정 맴버의 바코드 기준 Point 사용.
     * @param
     * @return
     */
    @ApiOperation(value="특정 바코드 기준으로 Point 사용 액션", notes="상점ID, 바코드, 적립금을 받아 처리한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/point")
    public UseResponseDTO usePoints(@RequestBody UseRequestDTO requestDTO){

        return usePointService.usePoints(requestDTO);
    }

}
