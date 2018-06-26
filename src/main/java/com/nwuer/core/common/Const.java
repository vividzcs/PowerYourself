package com.nwuer.core.common;

/**
 * @author vividzc
 * @date 2018/6/13 12:54
 */
public interface Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String TOKEN_PREFIX = "USER_TOKEN_";
    public static final String JOB_TOKEN_PREFIX = "TOKEN_";
    public static final Integer ID_LENGTH = 32;

    public static final Integer PAGE_SIZE = 8;

    //JOB
    public static final Integer JOB_NORMAL = 0;
    public static final Integer JOB_FINISHED = 1;
    public static final Integer JOB_OVER_DATE = 2;

    public static final String URL_PREFIX = "http://localhost:8080";

    public static final String CATEGORY_ROOT_ID = "00000000000000000000000000000000";

    public static final String INITAIL_PASSWORD = "111111";

    //register
    public static final Integer REGISTER_OVER_TIME = 30;
    public static final Integer TOKEN_OVER_TIME = 12;

}
