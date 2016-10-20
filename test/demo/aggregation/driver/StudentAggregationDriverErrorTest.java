package demo.aggregation.driver;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import demo.common.log.LoggingUtils;
import demo.common.util.FileUtils;

/**
 * The unit test for failed input file will stop at the failed line and will not go forward.
 * We only need to test the aggregation part.
 * 
 * @author richardl
 *
 */
public class StudentAggregationDriverErrorTest {

	@Before
    public void init() {
		LoggingUtils.initializeLogger();
    }
	
	/**
	 * Test the line has too many sections
	 */
	@Test
    public void testTooManySectionError() {
		LoggingUtils.logInfo(StudentAggregationDriverErrorTest.class, "Driver Error Test: Too many section error");
		String srcFile = new File(".").getAbsolutePath() + "\\input\\testFailed1.txt";
		FileUtils.mkdirs(srcFile);				
		try (BufferedInputStream in = new BufferedInputStream(
				new FileInputStream(srcFile));) {
			Map<String, Map> aggregateResult = StudentAggregationDriver.getDefault().aggregateRecords(in);
			//If the aggregateResult is null then the aggregation failed.
			assertNull(aggregateResult);
		} catch (FileNotFoundException fnfe) {
			LoggingUtils.logError(fnfe,
					"Cannot open file: " + fnfe.getMessage());
		} catch (IOException ioe) {
			LoggingUtils.logError(ioe, ioe.getMessage());
		}
	}
	
	/**
	 * Test the line has not enough sections
	 */
	@Test
    public void testNotEnoughSectionError() {
		LoggingUtils.logInfo(StudentAggregationDriverErrorTest.class, "Driver Error Test: Not enough section error");
		String srcFile = new File(".").getAbsolutePath() + "\\input\\testFailed2.txt";
		FileUtils.mkdirs(srcFile);
		try (BufferedInputStream in = new BufferedInputStream(
				new FileInputStream(srcFile));) {
			Map<String, Map> aggregateResult = StudentAggregationDriver.getDefault().aggregateRecords(in);
			//If the aggregateResult is null then the aggregation failed.
			assertNull(aggregateResult);
		} catch (FileNotFoundException fnfe) {
			LoggingUtils.logError(fnfe,
					"Cannot open file: " + fnfe.getMessage());
		} catch (IOException ioe) {
			LoggingUtils.logError(ioe, ioe.getMessage());
		}
	}
	
	/**
	 * Test the grade is wrong
	 * Last 2 tests are both for the grade+number cannot adapt to the format.
	 * More format validation can see the ValidationTest details.
	 */
	@Test
    public void testGradeFormatError() {
		LoggingUtils.logInfo(StudentAggregationDriverErrorTest.class, "Driver Error Test: Grade format error");
		String srcFile = new File(".").getAbsolutePath() + "\\input\\testFailed3.txt";
		FileUtils.mkdirs(srcFile);
		try (BufferedInputStream in = new BufferedInputStream(
				new FileInputStream(srcFile));) {
			Map<String, Map> aggregateResult = StudentAggregationDriver.getDefault().aggregateRecords(in);
			//If the aggregateResult is null then the aggregation failed.
			assertNull(aggregateResult);
		} catch (FileNotFoundException fnfe) {
			LoggingUtils.logError(fnfe,
					"Cannot open file: " + fnfe.getMessage());
		} catch (IOException ioe) {
			LoggingUtils.logError(ioe, ioe.getMessage());
		}
	}
	
	/**
	 * Test the grade+number too long
	 */
	@Test
    public void testGradeNumberFormatError() {
		LoggingUtils.logInfo(StudentAggregationDriverErrorTest.class, "Driver Error Test: grade+number too long error");
		String srcFile = new File(".").getAbsolutePath() + "\\input\\testFailed4.txt";
		FileUtils.mkdirs(srcFile);
		try (BufferedInputStream in = new BufferedInputStream(
				new FileInputStream(srcFile));) {
			Map<String, Map> aggregateResult = StudentAggregationDriver.getDefault().aggregateRecords(in);
			//If the aggregateResult is null then the aggregation failed.
			assertNull(aggregateResult);
		} catch (FileNotFoundException fnfe) {
			LoggingUtils.logError(fnfe,
					"Cannot open file: " + fnfe.getMessage());
		} catch (IOException ioe) {
			LoggingUtils.logError(ioe, ioe.getMessage());
		}
	}

}
