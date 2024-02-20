/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author moham
 */
public class RegexValidation {
    
    
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)"
            + "*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public static final String CHAR_PATTERN = "[A-Z]{1,}";
    public static final String NUMERIC_PATTERN = "[0-9]{1,}";
    public static final String NOT_CHAR_PATTERN = "[^\\w]{1,}";
    
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	if (email == null) {
            return false;
	}
	return pattern.matcher(email).matches();
    }
    
    public static boolean checkPasswordFormat(String regex, String input) {
	Pattern p;
        Matcher m;
	boolean found = false;
	p = Pattern.compile(regex);
	m = p.matcher(input);
	while (m.find()) {
            found = true;
	}
	return found;
    }
    public static boolean checkName( String name ) {
      return name.matches( "[A-Z][a-z]*" );
   }
}
