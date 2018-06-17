package com.nwuer.core.common.exception;

import com.nwuer.core.common.ResponseCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 自定义异常类
 * @author zengxiaogang
 * 在RuntimeException的基础上添加code
 */
@Getter
@Setter
@ToString
public class PowerYourselfException extends RuntimeException {
    /**
     * 错误异常码
     * 定义在
     * @see com.nwuer.core.common.ResponseCode
     */
    private int code;

    public PowerYourselfException(ResponseCode responseCode) {
        super(responseCode.getDesc());
        this.code = responseCode.getCode();
    }
}
