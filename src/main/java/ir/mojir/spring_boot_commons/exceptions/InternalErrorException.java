package ir.mojir.spring_boot_commons.exceptions;

import org.springframework.http.HttpStatus;

import ir.mojir.spring_boot_commons.dtos.ErrorDto;
import ir.mojir.spring_boot_commons.enums.ErrorEnum;

@SuppressWarnings("serial")
public class InternalErrorException extends ServiceException {
	public InternalErrorException(String message, Throwable e) {
		super(new ErrorDto(message, ErrorEnum.INTERNAL_ERROR), e);
		
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

}
