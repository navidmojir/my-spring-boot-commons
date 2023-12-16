package ir.mojir.spring_boot_commons.exceptions;

import org.springframework.http.HttpStatus;

import ir.mojir.spring_boot_commons.dtos.ErrorDto;

@SuppressWarnings("serial")
public abstract class ServiceException  extends RuntimeException{
	private ErrorDto error;

	public ServiceException(ErrorDto errorDto, Throwable e) {
		super(errorDto.getMessage(), e);
		this.error = errorDto;
	}

	public ErrorDto getError() {
		return error;
	}

	public abstract HttpStatus getHttpStatus();
	
	
}
