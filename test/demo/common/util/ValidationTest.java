package demo.common.util;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
    }

	@Test
    public void testLowerCase() {
		assertTrue(ValidationUtils.matchPattern("k1234", pattern));
	}
	
	@Test
    public void testUpperCase() {
		assertTrue(ValidationUtils.matchPattern("K0000", pattern));
	}
	
	@Test
    public void testNumber() {
		assertTrue(ValidationUtils.matchPattern("30000", pattern));
	}
	
	@Test
    public void testTooLong() {
		assertFalse(ValidationUtils.matchPattern("390000", pattern));
	}
	
	@Test
    public void testTooShort() {
		assertFalse(ValidationUtils.matchPattern("5145", pattern));
	}
	
	@Test
    public void testInvalidStart() {
		assertFalse(ValidationUtils.matchPattern("a1456", pattern));
	}
	

	private String pattern;
}
