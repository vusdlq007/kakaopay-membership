package com.kakaopay.earnpointapi.cmm.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND(404,"COMMON-ERR-404","PAGE NOT FOUND"),
    NOT_ALLOW(403,"COMMON-ERR-403","NOT ALLOW"),
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","INTER SERVER ERROR"),
    SUCCESS(200,"COMMON-SUCC-200","OK"),
    EMAIL_DUPLICATION(400,"MEMBER-ERR-400","EMAIL DUPLICATED");


    @Getter
    private int status;
    @Getter
    private String errorCode;
    @Getter
    private String message;

}
