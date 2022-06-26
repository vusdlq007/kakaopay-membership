package com.kakaopay.issueapi;


import com.kakaopay.issueapi.api.dto.CoreRequestDTO;
import com.kakaopay.issueapi.api.dto.CoreResponseDTO;
import com.kakaopay.issueapi.api.dto.MemberDTO;
import com.kakaopay.issueapi.api.repo.MemberRepository;
import com.kakaopay.issueapi.api.svc.BarcodeService;
import com.kakaopay.issueapi.api.svc.impl.BarcodeServiceImpl;
import com.kakaopay.issueapi.cmm.constant.ResponseCode;
import com.kakaopay.issueapi.cmm.constant.TypeConstant;
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

import static org.mockito.ArgumentMatchers.any;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class BarcodeServiceTest {


    BarcodeService barcodeService;

    @Autowired
    MemberRepository memberRepository;

    @Before
    public void before(){
        this.barcodeService = new BarcodeServiceImpl(memberRepository);
    }

    @Test
    @DisplayName("맴버가입 성공")
    public void issueBarcodeSuccess() throws Exception {

        CoreRequestDTO requestDTO = new CoreRequestDTO();
        ReflectionTestUtils.setField(requestDTO,"accessType", TypeConstant.ISSUE);
        ReflectionTestUtils.setField(requestDTO,"reqDetail",new MemberDTO(138942,"김윤권"));

        CoreResponseDTO responseDTO = new CoreResponseDTO();
        ReflectionTestUtils.setField(responseDTO,"resCode", ResponseCode.BARCODE_ISSUE_SUCCESS.getStatus());
        ReflectionTestUtils.setField(responseDTO,"accessType", TypeConstant.ISSUE);
        ReflectionTestUtils.setField(responseDTO,"resResult", ResponseCode.BARCODE_ISSUE_SUCCESS.getMessage());
        ReflectionTestUtils.setField(responseDTO,"resMessage", ResponseCode.BARCODE_ISSUE_SUCCESS.getErrorCode());

        // when
        //then
        Assertions.assertThat(barcodeService.issueBarcode(requestDTO).getResCode()).isEqualTo(responseDTO.getResCode());
    }


    @Test
    @DisplayName("맴버가입 실패, 기 존재 ID")
    public void issueBarcodeFailExistId() throws Exception {

        CoreRequestDTO requestDTO = new CoreRequestDTO();
        ReflectionTestUtils.setField(requestDTO,"accessType", TypeConstant.ISSUE);
        ReflectionTestUtils.setField(requestDTO,"reqDetail",new MemberDTO(34567,"김윤권"));

        CoreResponseDTO responseDTO = new CoreResponseDTO();
        ReflectionTestUtils.setField(responseDTO,"resCode", ResponseCode.BARCODE_SEARCH_EXIST.getStatus());
        ReflectionTestUtils.setField(responseDTO,"accessType", TypeConstant.ISSUE);
        ReflectionTestUtils.setField(responseDTO,"resResult", ResponseCode.BARCODE_SEARCH_EXIST.getMessage());
        ReflectionTestUtils.setField(responseDTO,"resMessage", ResponseCode.BARCODE_SEARCH_EXIST.getErrorCode());

        // when
        //then
        Assertions.assertThat(barcodeService.issueBarcode(requestDTO).getResCode()).isEqualTo(responseDTO.getResCode());
    }

    @Test
    @DisplayName("맴버가입 실패, 잘못된요청")
    public void issueBarcodeFail() throws Exception {

        CoreRequestDTO requestDTO = new CoreRequestDTO();
        ReflectionTestUtils.setField(requestDTO,"accessType", TypeConstant.ISSUE);
        ReflectionTestUtils.setField(requestDTO,"reqDetail",null);

        CoreResponseDTO responseDTO = new CoreResponseDTO();
        ReflectionTestUtils.setField(responseDTO,"resCode", ResponseCode.BARCODE_SEARCH_EXIST.getStatus());
        ReflectionTestUtils.setField(responseDTO,"accessType", TypeConstant.ISSUE);
        ReflectionTestUtils.setField(responseDTO,"resResult", ResponseCode.BARCODE_SEARCH_EXIST.getMessage());
        ReflectionTestUtils.setField(responseDTO,"resMessage", ResponseCode.BARCODE_SEARCH_EXIST.getErrorCode());

        // when
        //then
        Assertions.assertThat(barcodeService.issueBarcode(requestDTO).getResCode()).isEqualTo(responseDTO.getResCode());
    }
}
