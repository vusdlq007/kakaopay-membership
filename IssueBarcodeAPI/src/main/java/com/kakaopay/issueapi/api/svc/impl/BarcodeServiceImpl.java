package com.kakaopay.issueapi.api.svc.impl;

import com.kakaopay.issueapi.api.dto.CoreRequestDTO;
import com.kakaopay.issueapi.api.dto.CoreResponseDTO;
import com.kakaopay.issueapi.api.dto.MemberDTO;
import com.kakaopay.issueapi.api.repo.MemberRepository;
import com.kakaopay.issueapi.api.svc.BarcodeService;
import com.kakaopay.issueapi.api.vo.MemberVo;
import com.kakaopay.issueapi.cmm.constant.ResponseCode;
import com.kakaopay.issueapi.cmm.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class BarcodeServiceImpl implements BarcodeService {

    @Value("${custom.service.timezone}")
    String timeZone;

    @Autowired
    MemberRepository memberRepository;


    @Override
    @Transactional
    public CoreResponseDTO issueBarcode(CoreRequestDTO requestDto) {

        MemberVo memberVo = new MemberVo();
        // Member 랜덤 고유키 생성(9자리)
        long memberUuid = Util.generateUUID(9);
        long barcodeUuid = Util.generateUUID(10);
        LocalDateTime curTime = LocalDateTime.now(ZoneId.of(timeZone));

        memberVo.setMemberId((int) memberUuid);
        memberVo.setBarcode(String.valueOf(barcodeUuid));
        memberVo.setName(requestDto.getReqDetail().getName());
        memberVo.setCreatedAt(curTime);

        try {
            Optional<MemberVo> result = memberRepository.findByBarcode(memberVo.getBarcode());

            if(result.isEmpty()){
                memberRepository.save(memberVo);
            }else{
                return new CoreResponseDTO(ResponseCode.BARCODE_SEARCH_EXIST.getStatus(), ResponseCode.BARCODE_SEARCH_EXIST.getErrorCode(), requestDto);
            }

        } catch (Exception e) {
            log.error("ERRMSG ",e.getMessage());
            return new CoreResponseDTO(ResponseCode.BARCODE_SEARCH_FAIL.getStatus(), ResponseCode.BARCODE_SEARCH_FAIL.getErrorCode(), requestDto);
        }

        return new CoreResponseDTO(ResponseCode.BARCODE_ISSUE_SUCCESS.getStatus(), ResponseCode.BARCODE_ISSUE_SUCCESS.getErrorCode(), ResponseCode.BARCODE_ISSUE_SUCCESS.getMessage());
    }


    /**
     * 특정 맴버/바코드 정보 조회.
     *
     * @param requestDto
     * @return
     */
    @Override
    public CoreResponseDTO readBarcode(CoreRequestDTO requestDto) {

        MemberDTO memberDTO = new MemberDTO();
        try {
            Optional<MemberVo> memberVo = memberRepository.findById(requestDto.getReqDetail().getMemberId());

            memberDTO.setMemberId(memberVo.get().getMemberId());
            memberDTO.setName(memberVo.get().getName());
            memberDTO.setBarcode(memberVo.get().getBarcode());
            memberDTO.setCreatedAt(memberVo.get().getCreatedAt());

        } catch (Exception e) {
            log.error("ERRMSG ",e.getMessage());
            return new CoreResponseDTO(ResponseCode.BARCODE_SEARCH_FAIL.getStatus(), ResponseCode.BARCODE_SEARCH_FAIL.getErrorCode(), requestDto);
        }
        return new CoreResponseDTO(ResponseCode.BARCODE_SEARCH_SUCCESS.getStatus(), ResponseCode.BARCODE_SEARCH_SUCCESS.getErrorCode(), memberDTO);
    }


    /**
     * 모든 맴버/바코드 정보 조회.
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public CoreResponseDTO readAllBarcode(CoreRequestDTO requestDto) {


        List<MemberDTO> memberDTOList = new ArrayList<>();
        try {
            List<MemberVo> memberVos = memberRepository.findAll();

            if (memberVos.size() > 0) {
                for (MemberVo member : memberVos) {
                    MemberDTO memberDTO = new MemberDTO();
                    memberDTO.setMemberId(member.getMemberId());
                    memberDTO.setName(member.getName());
                    memberDTO.setBarcode(member.getBarcode());
                    memberDTO.setCreatedAt(member.getCreatedAt());
                    memberDTOList.add(memberDTO);
                }
            }

        } catch (Exception e) {
            log.error("ERRMSG ",e.getMessage());
            return new CoreResponseDTO(ResponseCode.BARCODE_SEARCH_FAIL.getStatus(), ResponseCode.BARCODE_SEARCH_FAIL.getErrorCode(), requestDto);
        }
        return new CoreResponseDTO(ResponseCode.BARCODE_SEARCH_SUCCESS.getStatus(), ResponseCode.BARCODE_SEARCH_SUCCESS.getErrorCode(), memberDTOList);
    }
}
