package com.nwuer.core.common;

/**
 * @author vividzc
 * @date 2018/6/8 16:50
 */

public enum ResponseCode {
	SUCCESS(0,"SUCCCESS"),
	ERROR(1,"ERROR"),
	NEED_LOGIN(10,"NEED_LOGIN"),
	ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");
	
	private final Integer code;
	private final String desc;
	
	private ResponseCode(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	
	
}
