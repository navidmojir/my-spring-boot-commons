package ir.mojir.spring_boot_commons.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class PersianDate {
	@Min(1200)
	@Max(1500)
    private int year;
	
	@Min(1)
	@Max(12)
    private int month;
	
	@Min(1)
	@Max(31)
    private int day;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

	@Override
	public String toString() {
		return  year + "/" + month + "/" + day;
	}
    
    
}
