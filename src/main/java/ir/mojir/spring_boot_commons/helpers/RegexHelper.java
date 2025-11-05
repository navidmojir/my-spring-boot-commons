package ir.mojir.spring_boot_commons.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * reference: https://github.com/mirhmousavi/Regex.Persian.Language
 * @author n.mojir
 *
 */
public class RegexHelper {

	public final static String persianAlphaCodepoints = "\u0621-\u0628\u062A-\u063A\u0641-\u0642\u0644-\u0648\u064E-\u0651\u0655\u067E\u0686\u0698\u06A9\u06AF\u06BE\u06CC";

	public final static String persianNumCodepoints = "\u06F0-\u06F9";
	
	public final static String spaceCodePoints = "\u0020\u2000-\u200F\u2028-\u202F";
	
	public final static String punctuationMarksCodepoints = "\u060C\u061B\u061F\u0640\u066A\u066B\u066C";
	
	public final static String additionalArabicCharactersCodepoints = "\u0629\u0643\u0649-\u064B\u064D\u06D5";
	
	public final static String arabic_numbers_codepoints = "\u0660-\u0669";
	
	
	
	public final static String persianTextRegex = "^[-:,;\\." + persianAlphaCodepoints + persianNumCodepoints + punctuationMarksCodepoints
			+ additionalArabicCharactersCodepoints + arabic_numbers_codepoints + spaceCodePoints +"a-zA-Z0-9\\s\\(\\)\\/]{0,255}$";

	public final static String persianTextRegexMessageFa = "فقط حروف فارسی، انگلیسی، اعداد، نشانه گذاری با حداکثر طول 255 حرف";
	public final static String persianLongTextRegex = "^[-:,;\\." + persianAlphaCodepoints + persianNumCodepoints + punctuationMarksCodepoints
			+ additionalArabicCharactersCodepoints + arabic_numbers_codepoints + spaceCodePoints +"a-zA-Z0-9\\s\\(\\)\\/]{0,1024}$";
	public final static String persianLongTextRegexMessageFa = "فقط حروف فارسی، انگلیسی، اعداد، نشانه گذاری با حداکثر طول 1024 حرف";
	public final static String persianFieldRegex = "^[-" + persianAlphaCodepoints + additionalArabicCharactersCodepoints + spaceCodePoints + "a-zA-Z0-9\\s\\.\\(\\)]{0,255}$";
	public final static String persianFieldRegexMessageFa = "فقط حروف فارسی، انگلیسی و اعداد با حداکثر طول 255 حرف";
	public final static String phoneRegex = "^[0-9]{0,15}";
	public final static String phoneRegexMessageFa = "فقط اعداد با حداکثر طول 15 عدد";
	
	public final static String mobileRegex = "^0[0-9]{10}$|^$";
	public final static String mobileRegexMessageFa = "مثال 09121234567";
	
	public final static String postalCodeRegex = "^[0-9]{10}$|^$";
	public final static String postalCodeRegexMessageFa = "کد پستی یک عدد ده رقمی است";
	
	public final static String patternDefaultMessage = "Input pattern not mached";
	
	public static String findStrByRegex(String str, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(str);
		while(m.find()) {
			return m.group(0);
		}
		return null;
	}
}
