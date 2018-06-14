package com.nwuer.core.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author vividzc
 * @date 2018/6/8 16:50
 */

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    NEED_LOGIN(10, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT"),
    UNKNOWN_ERROR(-1, "UNKNOWN_ERROR"),
    PASSWORD_FILL_IN_REQUIRED(1000, "PASSWORD_FILL_IN_REQUIRED"),
    CONFORM_PASSWORD_FILL_IN_REQUIRED(1001, "CONFORM_PASSWORD_FILL_IN_REQUIRED"),
    REGISTRATION_FORM_FILL_IN_INCORRECT(1002, "REGISTRATION_FORM_FILL_IN_INCORRECT"),
    EMAIL_SEND_ERROR(1003, "EMAIL_SEND_ERROR"),
    EMAIL_ALREADY_EXISTS(1004, "EMAIL_ALREADY_EXISTS"),
    USERNAME_ALREADY_EXISTS(1005, "USERNAME_ALREADY_EXISTS");

    private final Integer code;
    private final String desc;

}
