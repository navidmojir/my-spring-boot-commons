package ir.mojir.spring_boot_commons.exceptions;

import org.springframework.http.HttpStatus;

import ir.mojir.spring_boot_commons.dtos.ErrorDto;
import ir.mojir.spring_boot_commons.enums.ErrorEnum;

@SuppressWarnings("serial")
public class ForbiddenAccessException extends ServiceException{

	public ForbiddenAccessException(String message, Throwable e) {
		super(new ErrorDto(message, ErrorEnum.FORBIDDEN_ACCESS), e);
	}
	
	public ForbiddenAccessException(String message) {
		this(message, null);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.FORBIDDEN;
	}

}
