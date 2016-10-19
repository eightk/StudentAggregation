package demo.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
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
