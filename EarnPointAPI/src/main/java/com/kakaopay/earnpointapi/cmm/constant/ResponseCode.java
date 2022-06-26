package com.kakaopay.earnpointapi.cmm.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum ResponseCode {
    NOT_FOUND(404,"COMMON-ERR-404","PAGE NOT FOUND"),
    NOT_ALLOW(403,"COMMON-ERR-403","NOT ALLOW"),
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","INTER SERVER ERROR"),
    SUCCESS(200,"COMMON-SUCC-200","SUCCESS"),
    POINT_EARN_SUCCESS(200,"포인트 적립에 성공하였습니다.","SUCCESS"),
    POINT_EARN_FAIL(500,"포인트 적립에 실패하였습니다.","INTER SERVER ERROR"),
    POINT_SEARCH_SUCCESS(200,"포인트 조회에 성공하였습니다.","SUCCESS"),
    POINT_SEARCH_FAIL(500,"포인트 조회에 실패하였습니다. 등록된 바코드가 아니거나 포인트적립 정보가 존재하지 않습니다.","INTER SERVER ERROR"),
    POINT_NO_STORE_FAIL(500,"포인트 조회에 실패하였습니다. 상점 정보가 존재하지 않습니다.","INTER SERVER ERROR"),
    LOG_PUSH_SUCCESS(200,"포인트 사용 로그 기록에 성공하였습니다.","SUCCESS"),
    LOG_PUSH_FAIL(500,"포인트 사용 로그 기록에 실패하였습니다.","INTER SERVER ERROR"),
    BARCODE_SEARCH_EXIST(500,"해당 바코드는 이미 존재합니다.","INTER SERVER ERROR"),
    EMAIL_DUPLICATION(400,"MEMBER-ERR-400","EMAIL DUPLICATED");


    @Getter
    private int status;
    @Getter
    private String errorCode;
    @Getter
    private String message;

}
