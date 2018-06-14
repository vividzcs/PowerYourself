package com.nwuer.core.common.exception;

import com.nwuer.core.common.ResponseCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PowerYourselfException extends RuntimeException {
    private int code;

    public PowerYourselfException(ResponseCode responseCode) {
        super(responseCode.getDesc());
        this.code = responseCode.getCode();
    }
}
