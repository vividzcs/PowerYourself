package com.nwuer.core.common;

import java.io.Serializable;

/**
 * @author vividzc
 * @date 2018/6/8 16:50
 */

public class ServerResponse<T> implements Serializable {
	private Integer status;
	private String msg;
	private T data;
	
	private ServerResponse(Integer status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	private ServerResponse(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	private ServerResponse(Integer status, T data) {
		this.status = status;
		this.data = data;
	}
	
	private ServerResponse(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public T getData() {
		return data;
	}
	
	public static <T>ServerResponse<T> createBySuccess(){
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode());
	}
	
	public static <T>ServerResponse<T> createBySuccess(String message){
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),message);
	}
	
	public static <T>ServerResponse<T> createBySuccess(String message,T data){
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),message,data);
	}
	
	public static <T>ServerResponse<T> createBySuccess(T data){
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),data);
	}
	
	public static <T>ServerResponse<T> createByError(){
		return new ServerResponse<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
	}	
	
	public static <T>ServerResponse<T> createByErrorMessage(String message){
		return new ServerResponse<>(ResponseCode.ERROR.getCode(),message);
	}
	
	public static <T>ServerResponse<T> createByErrorMessage(Integer code,String message){
		return new ServerResponse<>(code,message);
	}
	
	
	
	
	
	
}
