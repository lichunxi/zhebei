/**
 * 
 */
package com.zhebei.webserver;

/**
 * @author lichunxi
 * 
 */
public class ErrorResult {
	/**
	 * 错误码
	 */
	private String code;

	/**
	 * 错误描述
	 */
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
