package ir.mojir.spring_boot_commons.dtos;

import ir.mojir.spring_boot_commons.interfaces.IError;

public class ErrorDto {
	private int code;
	private String message;
	private String error;
	
	
	
	public ErrorDto() {
		super();
	}
	public ErrorDto(String message, IError error) {
		super();
		this.code = error.getCode();
		this.message = message;
		this.error = error.getString();
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
