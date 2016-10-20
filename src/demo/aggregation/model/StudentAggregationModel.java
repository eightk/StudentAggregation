package demo.aggregation.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import demo.common.driver.AggregationDriver;
import demo.common.log.LoggingUtils;
import demo.common.model.AbstractAggregationModel;
import demo.common.util.FileUtils;

/**
 * The concrete model for AggregationControlPanel. Run the aggregation for the
 * driver.
 * 
 * @author richardl
 *
 */
public class StudentAggregationModel extends
		AbstractAggregationModel<AggregationDriver> {

	@Override
	public boolean aggregate() {
		boolean result = true;
		if (getInputFileName() != null && getOutputFileName() != null) {
			LoggingUtils.logInfo(StudentAggregationModel.class,
					"Aggregation start.");
			LoggingUtils.logInfo(StudentAggregationModel.class, "Input File: "
					+ getInputFileName());
			LoggingUtils.logInfo(StudentAggregationModel.class, "Output File: "
					+ getOutputFileName());
			FileUtils.mkdirs(getInputFileName());
			FileUtils.mkdirs(getOutputFileName());

			// try opening the source and destination file
			// with BufferedInputSteam and BufferedOutputStream
			// try-with-resources will automatically release FileReader object
			// these two objects
			try (BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(getInputFileName()));
					BufferedOutputStream out = new BufferedOutputStream(
							new FileOutputStream(getOutputFileName()))) {
				Map<String, Map> aggregateResult = getAggregationDriver()
						.aggregateRecords(in);
				if (aggregateResult != null) {
					getAggregationDriver().generateResultFile(aggregateResult, out);
					LoggingUtils.logInfo(StudentAggregationModel.class, "Aggregation success.");
				} else {
					LoggingUtils.logInfo(StudentAggregationModel.class, "Aggregation Failed.");
					result = false;
				}
			} catch (FileNotFoundException fnfe) {
				LoggingUtils.logError(fnfe,
						"Cannot open file: " + fnfe.getMessage());
				result = false;
			} catch (IOException ioe) {
				LoggingUtils.logError(ioe, ioe.getMessage());
				result = false;
			}			
		}
		return result;
	}

}
