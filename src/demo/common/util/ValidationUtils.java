package demo.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class to make validation function easier
 * 
 * @author richardl
 * 
 */
public class ValidationUtils {
	
	/**
	 * Method for detecting whether the value matches the pattern
	 * @param value
	 * @param pattern
	 * @return If the value matchs the pattern, return true otherwise return false
	 */
	public static Boolean matchPattern(String value, String pattern) {

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(value);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }
}
