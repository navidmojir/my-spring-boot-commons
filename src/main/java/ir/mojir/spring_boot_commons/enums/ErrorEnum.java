package ir.mojir.spring_boot_commons.enums;

import ir.mojir.spring_boot_commons.interfaces.IError;

public enum ErrorEnum implements IError{
	INTERNAL_ERROR(101),
	ENTITY_NOT_FOUND(102),
	INVALID_INPUT(103),
	FORBIDDEN_ACCESS(104)
	;
	

	ErrorEnum(int code) {
		this.code = code;
	}
	
	private int code;
	
	public String getString() {
		return this.name();
	}

	public int getCode() {
		return this.code;
	}
}
