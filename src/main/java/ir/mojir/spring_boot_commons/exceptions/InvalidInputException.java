package ir.mojir.spring_boot_commons.exceptions;

import org.springframework.http.HttpStatus;

import ir.mojir.spring_boot_commons.dtos.ErrorDto;
import ir.mojir.spring_boot_commons.enums.ErrorEnum;

@SuppressWarnings("serial")
public class InvalidInputException extends ServiceException{

	public InvalidInputException(String message, Throwable e) {
		super(new ErrorDto(message, ErrorEnum.INVALID_INPUT), e);
	}
	
	public InvalidInputException(String message) {
		this(message, null);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}
