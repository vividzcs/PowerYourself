package com.nwuer.core.common.util;


import com.nwuer.core.common.ResponseCode;
import com.nwuer.core.vo.ResultVo;

public class ResultUtil {

    public static ResultVo<Object> success(Object object) {
        return new ResultVo<>(100, "成功", object);
    }

    public static ResultVo<Object> success() {
        return success(null);
    }

    public static ResultVo error(int code, String message) {
        ResultVo resultVo = new ResultVo();
        resultVo.setMessage(message);
        resultVo.setCode(code);
        return resultVo;
    }

    public static ResultVo error(ResponseCode result) {
        ResultVo resultVo = new ResultVo();
        resultVo.setMessage(result.getDesc());
        resultVo.setCode(result.getCode());
        return resultVo;
    }
}
