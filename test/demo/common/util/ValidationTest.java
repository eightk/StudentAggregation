package demo.common.util;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import demo.common.log.LoggingUtils;
import demo.common.util.ValidationUtils;

/**
 * Test whether the default pattern works fine for different grade+number input values
 * 
 * @author richardl
 *
 */
public class ValidationTest {

	@Before
    public void init() {
        pattern = "(^[k-kK-K1-8]{1})(\\d{4})$";
        LoggingUtils.initializeLogger();
    }

	@Test
    public void testLowerCase() {
		LoggingUtils.logInfo(ValidationTest.class, "Grade+Number format test: lower case k as grade");
		assertTrue(ValidationUtils.matchPattern("k1234", pattern));
	}
	
	@Test
    public void testUpperCase() {
		LoggingUtils.logInfo(ValidationTest.class, "Grade+Number format test: Upper case k as grade");
		assertTrue(ValidationUtils.matchPattern("K0000", pattern));
	}
	
	@Test
    public void testNumber() {
		LoggingUtils.logInfo(ValidationTest.class, "Grade+Number format test: number as grade");
		assertTrue(ValidationUtils.matchPattern("30000", pattern));
	}
	
	@Test
    public void testTooLong() {
		LoggingUtils.logInfo(ValidationTest.class, "Grade+Number format test: length too long error");
		assertFalse(ValidationUtils.matchPattern("390000", pattern));
	}
	
	@Test
    public void testTooShort() {
		LoggingUtils.logInfo(ValidationTest.class, "Grade+Number format test: length not enough error");
		assertFalse(ValidationUtils.matchPattern("5145", pattern));
	}
	
	@Test
    public void testInvalidStart() {
		LoggingUtils.logInfo(ValidationTest.class, "Grade+Number format test: wrong grade code error");
		assertFalse(ValidationUtils.matchPattern("a1456", pattern));
	}
	
	@Test
    public void testEmptyString() {
		LoggingUtils.logInfo(ValidationTest.class, "Empty String test: empty string error");
		assertFalse(ValidationUtils.matchPattern("", pattern));
	}
	

	private String pattern;
}
