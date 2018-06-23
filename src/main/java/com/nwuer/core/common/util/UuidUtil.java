package com.nwuer.core.common.util;

import java.util.UUID;

/**
 * @author vividzc
 * @date 2018/6/23 12:39
 */
public class UuidUtil {

    public static String get32UUID(){
        String uuid= UUID.randomUUID().toString().trim().replaceAll("-","");
        return uuid;
    }
}
