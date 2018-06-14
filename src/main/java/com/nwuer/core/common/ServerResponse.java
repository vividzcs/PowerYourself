package com.nwuer.core.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author vividzc
 * @date 2018/6/8 16:50
 */

@Getter
@ToString
public class ServerResponse<T> implements Serializable {
	private Integer status;  //返回状态码
	private String msg; //返回的信息
	private T data;  //返回的data,可以是实体类,字符串等等

	/**
	 * 根据状态码和状态码描述和返回数据创建服务器响应
	 * @param status
	 * @param msg
	 * @param data
	 */
	private ServerResponse(Integer status,String msg,T data){
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	/**
	 * 状态码和状态码描述创建服务器响应
	 * @param status
	 * @param msg
	 */
	private ServerResponse(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	/**
	 * 根据状态码和返回数据创建服务器响应
	 * @param status
	 * @param data
	 */
	private ServerResponse(Integer status, T data) {
		this.status = status;
		this.data = data;
	}

	/**
	 * 根据状态码创建服务器响应
	 * @param status
	 */
	private ServerResponse(Integer status) {
		this.status = status;
	}

	/**
	 * 成功时返回的服务器响应(只有状态码)
	 * @param <T>
	 * @return
	 */
	public static <T>ServerResponse<T> createBySuccess(){
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode());
	}

	/**
	 * 成功时返回的服务器响应(带状态码和自定义状态码描述)
	 * @param message
	 * @param <T>
	 * @return
	 */
	public static <T>ServerResponse<T> createBySuccess(String message){
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),message);
	}

	/**
	 * 成功时返回的服务器响应(带状态码,自定义状态码描述和数据)
	 * @param message
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T>ServerResponse<T> createBySuccess(String message,T data){
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),message,data);
	}

	/**
	 * 成功时返回的服务器响应(带状态码和数据)
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T>ServerResponse<T> createBySuccess(T data){
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),data);
	}

	/**
	 * 失败时返回的服务器响应(带状态码和系统状态码描述)
	 * @param <T>
	 * @return
	 */
	public static <T>ServerResponse<T> createByError(){
		return new ServerResponse<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
	}

	/**
	 * 失败时返回的服务器响应(带状态码和自定义状态码描述)
	 * @param message
	 * @param <T>
	 * @return
	 */
	public static <T>ServerResponse<T> createByErrorMessage(String message){
		return new ServerResponse<>(ResponseCode.ERROR.getCode(),message);
	}

	/**
	 * 失败时返回的服务器响应(带自定义状态码和自定义状态码描述)
	 * @param code
	 * @param message
	 * @param <T>
	 * @return
	 */
	public static <T>ServerResponse<T> createByErrorMessage(Integer code,String message){
		return new ServerResponse<>(code,message);
	}

	/**
	 * 判断此次请求是否成功
	 * @return
	 */
	@JsonIgnore
	//使之不在json序列化结果当中
	public boolean isSuccess(){
		return this.status == ResponseCode.SUCCESS.getCode();
	}



}
