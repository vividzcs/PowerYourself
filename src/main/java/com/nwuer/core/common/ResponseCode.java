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
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");

    private final Integer code;
    private final String desc;

}
