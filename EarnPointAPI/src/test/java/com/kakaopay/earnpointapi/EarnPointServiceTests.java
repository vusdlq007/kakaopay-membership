package com.kakaopay.earnpointapi;

import com.kakaopay.earnpointapi.api.dto.EarnRequestDTO;
import com.kakaopay.earnpointapi.api.dto.EarnResponseDTO;
import com.kakaopay.earnpointapi.api.repo.MemberRepository;
import com.kakaopay.earnpointapi.api.repo.PointRepository;
import com.kakaopay.earnpointapi.api.repo.StoreRepository;
import com.kakaopay.earnpointapi.api.svc.EarnPointService;
import com.kakaopay.earnpointapi.api.svc.impl.EarnPointServiceImpl;
import com.kakaopay.earnpointapi.cmm.constant.ResponseCode;
import com.kakaopay.earnpointapi.cmm.constant.TypeConstant;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class EarnPointServiceTests {

    EarnPointService earnPointService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    StoreRepository storeRepository;

    @Before
    public void before(){
        this.earnPointService = new EarnPointServiceImpl(memberRepository,pointRepository,storeRepository);
    }

    @Test
    @DisplayName("포인트 적립 성공")
    public void issueBarcodeSuccess() throws Exception {

        EarnRequestDTO requestDTO = new EarnRequestDTO();
        ReflectionTestUtils.setField(requestDTO,"accessType", TypeConstant.EARN);
        ReflectionTestUtils.setField(requestDTO,"storeId","1");
        ReflectionTestUtils.setField(requestDTO,"categoryId", Long.valueOf(2));
        ReflectionTestUtils.setField(requestDTO,"storeName","올리브영");
        ReflectionTestUtils.setField(requestDTO,"barcode","4516332037");
        ReflectionTestUtils.setField(requestDTO,"earnPoint", 1500);

        EarnResponseDTO responseDTO = new EarnResponseDTO();
        ReflectionTestUtils.setField(responseDTO,"resCode", ResponseCode.POINT_EARN_SUCCESS.getStatus());
        ReflectionTestUtils.setField(responseDTO,"accessType", TypeConstant.EARN);

        // when
        //then
        Assertions.assertThat(earnPointService.earnPoints(requestDTO).getResCode()).isEqualTo(responseDTO.getResCode());
    }

    @Test
    @DisplayName("포인트 적립 실패")
    public void earnPointFail() throws Exception {

        EarnRequestDTO requestDTO = new EarnRequestDTO();
        ReflectionTestUtils.setField(requestDTO,"accessType", TypeConstant.EARN);
        ReflectionTestUtils.setField(requestDTO,"storeId","2");
        ReflectionTestUtils.setField(requestDTO,"categoryId",2);
        ReflectionTestUtils.setField(requestDTO,"storeName","올리브영");
        ReflectionTestUtils.setField(requestDTO,"barcode","4516332037");
        ReflectionTestUtils.setField(requestDTO,"earnPoint",1500);


        EarnResponseDTO responseDTO = new EarnResponseDTO();
        ReflectionTestUtils.setField(responseDTO,"resCode", ResponseCode.POINT_EARN_FAIL.getStatus());
        ReflectionTestUtils.setField(responseDTO,"accessType", TypeConstant.EARN);

        // when
        //then
        Assertions.assertThat(earnPointService.earnPoints(requestDTO).getResCode()).isEqualTo(responseDTO.getResCode());
    }
}
