package ir.mojir.spring_boot_commons.dtos;

import javax.validation.constraints.Pattern;

public class Sorting {
	private boolean ascending;
	
	@Pattern(regexp = "^[a-zA-Z]*$", message = "sortField is invalid")
	private String sortField;
	
	
	public Sorting() {
		super();
	}
	public Sorting(Boolean ascending, String sortField) {
		this.ascending = ascending == null ? false: ascending;
		this.sortField = sortField;
	}
	public boolean isAscending() {
		return ascending;
	}
	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	
	
}
