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
    SUCCESS(0, "成功"),
    ERROR(1, "失败"),
    NEED_LOGIN(10, "需要登录"),
    TOKEN_FILED(11,"TOKEN失效"),
    ILLEGAL_ARGUMENT(2, "非法的参数"),
    UNKNOWN_ERROR(3, "未知错误"),
    DATE_PARSE_FILED(100,"日期解析错误"),
    DATA_DUPLICATION(999,"数据重复错误"),
    PASSWORD_FILL_IN_REQUIRED(1000, "PASSWORD_FILL_IN_REQUIRED"),
    CONFORM_PASSWORD_FILL_IN_REQUIRED(1001, "CONFORM_PASSWORD_FILL_IN_REQUIRED"),
    REGISTRATION_FORM_FILL_IN_INCORRECT(1002, "REGISTRATION_FORM_FILL_IN_INCORRECT"),
    EMAIL_SEND_ERROR(1003, "邮件发送错误"),
    EMAIL_ALREADY_EXISTS(1004, "邮件已经存在"),
    USERNAME_ALREADY_EXISTS(1005, "用户名已经存在"),
    IS_PAST_DATE(1006,"日期为过去时间"),
    JOB_HAS_REMINDED(1007,"任务已经提醒过,设置的时候没有设置重复");

    private final Integer code;
    private final String desc;

}
