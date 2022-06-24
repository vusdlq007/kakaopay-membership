package com.kakaopay.issueapi.api.ctr;

import com.kakaopay.issueapi.api.svc.BarcodeService;
import com.kakaopay.issueapi.api.dto.CoreRequestDTO;
import com.kakaopay.issueapi.api.dto.CoreResponseDTO;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@Api(tags = {"바코드 발급 관련 서비스 컨트롤러"})
@SwaggerDefinition(tags = {
        @Tag(name = "통합 바코드 서비스 컨트롤러", description = "바코드 발급 이용에 필요한 기능 제공")
})
@RequestMapping("/api/issue")
@RestController
public class BarcodeRestController {

    @Autowired
    private BarcodeService barcodeService;

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
     * 특정 Member 정보 조회.
     * @param
     * @return
     */
    @ApiOperation(value="특정 Member 정보 조회", notes=" ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/barcode")
    public CoreResponseDTO searchBarcode(@RequestBody CoreRequestDTO requestDTO){

        return barcodeService.readBarcode(requestDTO);
    }

    /**
     * Member 모두 조회.
     * @param
     * @return
     */
    @ApiOperation(value="Member 모두 조회", notes=" ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/barcode/all")
    public CoreResponseDTO searchAllBarcode(@RequestBody CoreRequestDTO requestDTO){

        return barcodeService.readAllBarcode(requestDTO);
    }


    /**
     * Member 생성 & 바코드 발급.
     * @param
     * @return
     */
    @ApiOperation(value="Member Id & Barcode 생성", notes=" ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/barcode")
    public CoreResponseDTO issueBarcode(@RequestBody CoreRequestDTO requestDTO){

        log.debug("Y_TEST requestBody ["+requestDTO+"]");

        return barcodeService.issueBarcode(requestDTO);
    }

}
