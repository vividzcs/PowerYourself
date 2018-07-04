package com.nwuer.core.common.handler;

import com.nwuer.core.common.ResponseCode;
import com.nwuer.core.common.ServerResponse;
import com.nwuer.core.common.exception.PowerYourselfException;
import lombok.extern.java.Log;
import org.quartz.ObjectAlreadyExistsException;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

/**
 * 自定义异常处理类
 * @author zengxiaogang
 */
@RestControllerAdvice
@Log
public class PowerYourselfExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Exception e){
        ModelAndView mv = new ModelAndView();
        ServerResponse res ;
        if(e instanceof PowerYourselfException) {
            PowerYourselfException powerYourselfException = (PowerYourselfException) e;
            res = ServerResponse.createByErrorMessage(powerYourselfException.getCode(),powerYourselfException.getMessage());
        } else if (e.getCause() instanceof PowerYourselfException) {
            PowerYourselfException powerYourselfException = (PowerYourselfException) e.getCause();
            res = ServerResponse.createByErrorMessage(powerYourselfException.getCode(),powerYourselfException.getMessage());
        } else if(e instanceof ExecutionException) {
            res = ServerResponse.createByErrorMessage(ResponseCode.TOKEN_FILED.getCode(),ResponseCode.TOKEN_FILED.getDesc());
        }else if(e instanceof SchedulerException) {
            res = ServerResponse.createByErrorMessage(ResponseCode.IS_PAST_DATE.getCode(),ResponseCode.IS_PAST_DATE.getDesc());
        } else if(e instanceof ParseException) {
            res = ServerResponse.createByErrorMessage(ResponseCode.DATE_PARSE_FILED.getCode(), ResponseCode.DATE_PARSE_FILED.getDesc());
        }else if(e instanceof ObjectAlreadyExistsException){
            res = ServerResponse.createByErrorMessage(ResponseCode.DATA_DUPLICATION.getCode(),ResponseCode.DATA_DUPLICATION.getDesc());
        }else{
            log.log(Level.SEVERE, "【系统异常】", e);
            res = ServerResponse.createByErrorMessage(ResponseCode.UNKNOWN_ERROR.getCode(),ResponseCode.UNKNOWN_ERROR.getDesc());
        }
        mv.setViewName("/error");
        mv.addObject("msg",res.getMsg());
        return mv;
    }
}
