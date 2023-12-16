package ir.mojir.spring_boot_commons.helpers;

public class Validations {
	public static boolean isNull(Object in)
	{
		if(in == null)
			return true;
		return false;
	}
	
	public static boolean isEmpty(String str)
	{
		if(isNull(str))
			return true;
		if(str.length() == 0)
			return true;
		return false;
	}
	
	/**
	 * if str trimmed length is empty returns true
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str)
	{
		if(isEmpty(str))
			return true;
		if(str.trim().length() == 0)
			return true;
		return false;
	}
}
