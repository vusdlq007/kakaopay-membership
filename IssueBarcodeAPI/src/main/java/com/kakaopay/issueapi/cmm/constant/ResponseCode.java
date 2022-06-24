package com.kakaopay.issueapi.cmm.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum ResponseCode {
    NOT_FOUND(404,"COMMON-ERR-404","PAGE NOT FOUND"),
    NOT_ALLOW(403,"COMMON-ERR-403","NOT ALLOW"),
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","INTER SERVER ERROR"),
    SUCCESS(200,"COMMON-SUCC-200","SUCCESS"),
    BARCODE_ISSUE_SUCCESS(200,"바코드 발급에 성공하였습니다.","SUCCESS"),
    BARCODE_ISSUE_FAIL(500,"바코드 발급에 실패하였습니다.","INTER SERVER ERROR"),
    BARCODE_SEARCH_SUCCESS(200,"바코드 조회에 성공하였습니다.","SUCCESS"),
    BARCODE_SEARCH_FAIL(500,"바코드 조회에 실패하였습니다.","INTER SERVER ERROR"),
    BARCODE_SEARCH_EXIST(500,"해당 바코드는 이미 존재합니다.","INTER SERVER ERROR"),
    EMAIL_DUPLICATION(400,"MEMBER-ERR-400","EMAIL DUPLICATED");


    @Getter
    private int status;
    @Getter
    private String errorCode;
    @Getter
    private String message;

}
