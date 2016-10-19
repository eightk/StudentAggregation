package demo.aggregation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import demo.aggregation.driver.StudentAggregationDriver;
import demo.common.log.LoggingUtils;

public class AggregationApp {

	public static void main(String[] args) {
        String srcFile = new File(".").getAbsolutePath() + "\\testSample.txt";
        String dstFile = new File(".").getAbsolutePath() + "\\resultSample.txt";
        
        // Initialize the LoggingUtils
        LoggingUtils.initializeLogger();
        LoggingUtils.logInfo(AggregationApp.class, "Aggregation App start.");
        // try opening the source and destination file
        // with BufferedInputSteam and BufferedOutputStream
        // try-with-resources will automatically release FileReader object these two objects
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(srcFile));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dstFile))) {
            StudentAggregationDriver.getDefault().aggregateRecords(in, out);
        } catch (FileNotFoundException fnfe) {
            LoggingUtils.logError(fnfe, "Cannot open file: " + fnfe.getMessage());
        } catch (IOException ioe) {
            LoggingUtils.logError(ioe, ioe.getMessage());
        }
    }
}
