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
import demo.common.util.ValidationUtils;

/**
 * The unit test for success input file.
 * We only need to test the aggregation part.
 * The input will include empty province, empty city, empty school, 
 * upper case and lower case k for grade 
 * 
 * @author richardl
 *
 */
public class StudentAggregationDriverSuccessTest {

	@Before
    public void init() {
		LoggingUtils.initializeLogger();
    }
	
	/**
	 * Test a normal input file include empty province, empty city, empty school, 
	 * upper case and lower case k for grade 
	 * 
	 */
	@Test
    public void testSuccess() {
		LoggingUtils.logInfo(StudentAggregationDriverSuccessTest.class, "Driver Success Test: Normal Input");
		String srcFile = new File(".").getAbsolutePath() + "\\input\\testSuccess1.txt";
		FileUtils.mkdirs(srcFile);
		try (BufferedInputStream in = new BufferedInputStream(
				new FileInputStream(srcFile));) {
			Map<String, Map> aggregateResult = StudentAggregationDriver.getDefault().aggregateRecords(in);
			//If the aggregateResult is not null then the aggregation success.
			assertNotNull(aggregateResult);
		} catch (FileNotFoundException fnfe) {
			LoggingUtils.logError(fnfe,
					"Cannot open file: " + fnfe.getMessage());
		} catch (IOException ioe) {
			LoggingUtils.logError(ioe, ioe.getMessage());
		}
	}
	
	/**
	 * Test the input file with empty lines in between two blocks of valid data
	 * We should skip the empty lines and continue the process
	 * 
	 */
	@Test
    public void testEmptyLineInBetween() {
		LoggingUtils.logInfo(StudentAggregationDriverSuccessTest.class, "Driver Success Test: Skip the empty lines in between");
		String srcFile = new File(".").getAbsolutePath() + "\\input\\testSuccess2.txt";
		FileUtils.mkdirs(srcFile);
		try (BufferedInputStream in = new BufferedInputStream(
				new FileInputStream(srcFile));) {
			Map<String, Map> aggregateResult = StudentAggregationDriver.getDefault().aggregateRecords(in);
			//If the aggregateResult is not null then the aggregation success.
			assertNotNull(aggregateResult);
		} catch (FileNotFoundException fnfe) {
			LoggingUtils.logError(fnfe,
					"Cannot open file: " + fnfe.getMessage());
		} catch (IOException ioe) {
			LoggingUtils.logError(ioe, ioe.getMessage());
		}
	}
	
	/**
	 * Test the input file with empty lines by the end of the file
	 * Make sure it will not trap in infinite loop, should be able to 
	 * skip the empty lines and finish the aggregation properly
	 * 
	 */
	@Test
    public void testEmptyLineByTheEnd() {
		LoggingUtils.logInfo(StudentAggregationDriverSuccessTest.class, "Driver Success Test: Skip the empty lines by the end");
		String srcFile = new File(".").getAbsolutePath() + "\\input\\testSuccess3.txt";
		FileUtils.mkdirs(srcFile);
		try (BufferedInputStream in = new BufferedInputStream(
				new FileInputStream(srcFile));) {
			Map<String, Map> aggregateResult = StudentAggregationDriver.getDefault().aggregateRecords(in);
			//If the aggregateResult is not null then the aggregation success.
			assertNotNull(aggregateResult);
		} catch (FileNotFoundException fnfe) {
			LoggingUtils.logError(fnfe,
					"Cannot open file: " + fnfe.getMessage());
		} catch (IOException ioe) {
			LoggingUtils.logError(ioe, ioe.getMessage());
		}
	}
	
	/**
	 * The combination of 2 and 3 and plus there are spaces in those empty lines
	 * Should still skip the lines and finish the progress properly.
	 */
	@Test
    public void testEmptyLineWithSpaceInBetweenAndByTheEnd() {
		LoggingUtils.logInfo(StudentAggregationDriverSuccessTest.class, "Driver Success Test: Skip the space line in between and by the end");
		String srcFile = new File(".").getAbsolutePath() + "\\input\\testSuccess4.txt";
		FileUtils.mkdirs(srcFile);
		try (BufferedInputStream in = new BufferedInputStream(
				new FileInputStream(srcFile));) {
			Map<String, Map> aggregateResult = StudentAggregationDriver.getDefault().aggregateRecords(in);
			//If the aggregateResult is not null then the aggregation success.
			assertNotNull(aggregateResult);
		} catch (FileNotFoundException fnfe) {
			LoggingUtils.logError(fnfe,
					"Cannot open file: " + fnfe.getMessage());
		} catch (IOException ioe) {
			LoggingUtils.logError(ioe, ioe.getMessage());
		}
	}
	
}
