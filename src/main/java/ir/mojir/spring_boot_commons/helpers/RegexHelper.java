package ir.mojir.spring_boot_commons.helpers;

/**
 * reference: https://github.com/mirhmousavi/Regex.Persian.Language
 * @author n.mojir
 *
 */
public class RegexHelper {

	public final static String persianAlphaCodepoints = "\u0621-\u0628\u062A-\u063A\u0641-\u0642\u0644-\u0648\u064E-\u0651\u0655\u067E\u0686\u0698\u06A9\u06AF\u06BE\u06CC";

	public final static String persianNumCodepoints = "\u06F0-\u06F9";
	
	public final static String punctuationMarksCodepoints = "\u060C\u061B\u061F\u0640\u066A\u066B\u066C";
	
	public final static String additionalArabicCharactersCodepoints = "\u0629\u0643\u0649-\u064B\u064D\u06D5";
	
	public final static String arabic_numbers_codepoints = "\u0660-\u0669";
	
	
	
	public final static String persianTextRegex = "^[-;\\." + persianAlphaCodepoints + persianNumCodepoints + punctuationMarksCodepoints 
			+ additionalArabicCharactersCodepoints + arabic_numbers_codepoints +"a-zA-Z1-9\\s]{0,255}$";
	
	public final static String persianFieldRegex = "^[" + persianAlphaCodepoints + additionalArabicCharactersCodepoints + "a-zA-Z0-9\\s]{0,255}$";

	public final static String phoneRegex = "^[0-9]{11}|[0-9]{8}$";
	
	public final static String mobileRegex = "^0[0-9]{10}$";
	
	public final static String patternDefaultMessage = "Input pattern not mached";
}
