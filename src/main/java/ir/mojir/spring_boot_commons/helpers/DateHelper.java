package ir.mojir.spring_boot_commons.helpers;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {
	public static Date normalizeTimeUp(Date date) {
		if(date == null)
			return null;
		Calendar input = Calendar.getInstance();
		input.setTime(date);
		return normalizeTimeUp(input).getTime();
	}
	
	public static Date normalizeTimeDown(Date date) {
		if(date == null)
			return null;
		Calendar input = Calendar.getInstance();
		input.setTime(date);
		return normalizeTimeDown(input).getTime();
	}
	
	public static Calendar normalizeTimeUp(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 900);

        return calendar;
    }
	
	public static Calendar normalizeTimeDown(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;
    }
}
