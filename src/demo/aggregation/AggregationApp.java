package demo.aggregation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import demo.aggregation.driver.StudentAggregationDriver;
import demo.common.log.LoggingUtils;
import demo.common.util.FileUtils;

/**
 * This is the main method for getting the input from srcFile then generating the output in dstFile
 * 
 * @author $Author: richardl $
 */
public class AggregationApp {

	public static void main(String[] args) {
		// Initialize the LoggingUtils
		LoggingUtils.initializeLogger();
		String srcFile = new File(".").getAbsolutePath() + "\\input\\testSample3.txt";
		String dstFile = new File(".").getAbsolutePath() + "\\output\\result-testSample3.txt";
		
		FileUtils.mkdirs(srcFile);
		FileUtils.mkdirs(dstFile);
		
		LoggingUtils.logInfo(AggregationApp.class, "Aggregation App start.");
		LoggingUtils.logInfo(AggregationApp.class, "Input File: " + srcFile);
		LoggingUtils.logInfo(AggregationApp.class, "Output File: " + dstFile);
		// try opening the source and destination file with BufferedInputSteam and BufferedOutputStream
		// try-with-resources will automatically release FileReader object these two objects
		try (BufferedInputStream in = new BufferedInputStream(
				new FileInputStream(srcFile));
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(dstFile))) {
			// getting the input from file and generating the results as output
			Map<String, Map> aggregateResult = StudentAggregationDriver.getDefault().aggregateRecords(in);
			// based on the result from the input aggregation, print the output
			StudentAggregationDriver.getDefault().generateResultFile(aggregateResult, out);
		} catch (FileNotFoundException fnfe) {
			LoggingUtils.logError(fnfe,
					"Cannot open file: " + fnfe.getMessage());
		} catch (IOException ioe) {
			LoggingUtils.logError(ioe, ioe.getMessage());
		}
		LoggingUtils.logInfo(AggregationApp.class, "Aggregation App finish.");
	}
}
