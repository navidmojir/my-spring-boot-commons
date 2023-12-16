package ir.mojir.spring_boot_commons.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class Paging {	
	@PositiveOrZero
	private int pageNumber;
		
	@Min(1)
	@Positive
	private int pageSize;
	
	
	
	public Paging() {
		this.pageNumber = 0;
		this.pageSize = 5;
	}
	public Paging(Integer pageNumber, Integer pageSize) {
		this.pageNumber = pageNumber == null ? 0 : pageNumber;
		this.pageSize = pageSize == null ? 5 : pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
