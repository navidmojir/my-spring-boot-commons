package ir.mojir.spring_boot_commons.exceptions;

import org.springframework.http.HttpStatus;

import ir.mojir.spring_boot_commons.dtos.ErrorDto;
import ir.mojir.spring_boot_commons.enums.ErrorEnum;

@SuppressWarnings("serial")
public class EntityNotFoundException extends ServiceException{

	public EntityNotFoundException(int id, Throwable e) {
		this((long)id, e);
	}
	
	public EntityNotFoundException(long id, Throwable e) {
		this("The entity with id " + id + " was not found", e);
	}
	
	public EntityNotFoundException(String message, Throwable e) {
		super(new ErrorDto(message, ErrorEnum.ENTITY_NOT_FOUND), e);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.NOT_FOUND;
	}

}
