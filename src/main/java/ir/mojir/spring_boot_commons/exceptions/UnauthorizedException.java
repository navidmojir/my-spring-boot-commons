package ir.mojir.spring_boot_commons.exceptions;

import org.springframework.http.HttpStatus;

import ir.mojir.spring_boot_commons.dtos.ErrorDto;
import ir.mojir.spring_boot_commons.enums.ErrorEnum;

@SuppressWarnings("serial")
public class UnauthorizedException extends ServiceException{

	public UnauthorizedException(String message, Throwable e) {
		super(new ErrorDto(message, ErrorEnum.UNAUTHORIZED), e);
	}
	
	public UnauthorizedException(String message) {
		this(message, null);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.UNAUTHORIZED;
	}

}
