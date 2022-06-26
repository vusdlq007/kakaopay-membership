package com.kakaopay.usepointapi;

import com.kakaopay.usepointapi.api.dto.UseRequestDTO;
import com.kakaopay.usepointapi.api.dto.UseResponseDTO;
import com.kakaopay.usepointapi.api.repo.MemberRepository;
import com.kakaopay.usepointapi.api.repo.PointRepository;
import com.kakaopay.usepointapi.api.repo.StoreRepository;
import com.kakaopay.usepointapi.api.svc.UsePointService;
import com.kakaopay.usepointapi.api.svc.impl.UsePointServiceImpl;
import com.kakaopay.usepointapi.cmm.constant.ResponseCode;
import com.kakaopay.usepointapi.cmm.constant.TypeConstant;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class UsePointServiceTests {

    UsePointService usePointService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    StoreRepository storeRepository;

    @Before
    public void before(){
        this.usePointService = new UsePointServiceImpl(memberRepository,pointRepository,storeRepository);
    }

    @org.junit.Test
    @DisplayName("포인트 사용 성공")
    public void usePointSuccess() throws Exception {

        UseRequestDTO requestDTO = new UseRequestDTO();
        ReflectionTestUtils.setField(requestDTO,"accessType", TypeConstant.USE);
        ReflectionTestUtils.setField(requestDTO,"storeId","1");
        ReflectionTestUtils.setField(requestDTO,"categoryId", Long.valueOf(2));
        ReflectionTestUtils.setField(requestDTO,"storeName","올리브영");
        ReflectionTestUtils.setField(requestDTO,"barcode","4516332037");
        ReflectionTestUtils.setField(requestDTO,"usePoint", 1500);

        UseResponseDTO responseDTO = new UseResponseDTO();
        ReflectionTestUtils.setField(responseDTO,"resCode", ResponseCode.POINT_USE_SUCCESS.getStatus());
        ReflectionTestUtils.setField(responseDTO,"accessType", TypeConstant.USE);

        // when
        //then
        Assertions.assertThat(usePointService.usePoints(requestDTO).getResCode()).isEqualTo(responseDTO.getResCode());
    }

    @Test
    @DisplayName("포인트 사용 실패")
    public void usePointFail() throws Exception {

        UseRequestDTO requestDTO = new UseRequestDTO();
        ReflectionTestUtils.setField(requestDTO,"accessType", TypeConstant.USE);
        ReflectionTestUtils.setField(requestDTO,"storeId","1");
        ReflectionTestUtils.setField(requestDTO,"categoryId", Long.valueOf(2));
        ReflectionTestUtils.setField(requestDTO,"storeName","올리브영");
        ReflectionTestUtils.setField(requestDTO,"barcode","4516332037");
        ReflectionTestUtils.setField(requestDTO,"usePoint", 988990);

        UseResponseDTO responseDTO = new UseResponseDTO();
        ReflectionTestUtils.setField(responseDTO,"resCode", ResponseCode.POINT_USE_NONE_FAIL.getStatus());
        ReflectionTestUtils.setField(responseDTO,"accessType", TypeConstant.USE);

        // when
        //then
        Assertions.assertThat(usePointService.usePoints(requestDTO).getResCode()).isEqualTo(responseDTO.getResCode());
    }

}
