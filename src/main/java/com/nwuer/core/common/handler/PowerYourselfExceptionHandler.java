package com.nwuer.core.common.handler;

import com.nwuer.core.common.ResponseCode;
import com.nwuer.core.common.exception.PowerYourselfException;
import com.nwuer.core.common.util.ResultUtil;
import com.nwuer.core.vo.ResultVo;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Level;

@RestControllerAdvice
@Log
public class PowerYourselfExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultVo handle(Exception e) {
        if (e instanceof PowerYourselfException) {
            PowerYourselfException powerYourselfException = (PowerYourselfException) e;
            return ResultUtil.error(powerYourselfException.getCode(), powerYourselfException.getMessage());
        } else if (e.getCause() instanceof PowerYourselfException) {
            PowerYourselfException powerYourselfException = (PowerYourselfException) e.getCause();
            return ResultUtil.error(powerYourselfException.getCode(), powerYourselfException.getMessage());
        } else {
            log.log(Level.SEVERE, "【系统异常】", e);
            return ResultUtil.error(ResponseCode.UNKNOWN_ERROR);
        }
    }
}
