package demo.aggregation.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import demo.common.driver.AggregationDriver;
import demo.common.log.LoggingUtils;
import demo.common.model.AbstractAggregationModel;

public class StudentAggregationModel extends
		AbstractAggregationModel<AggregationDriver> {

	@Override
	public void aggregate() {
		if (getInputFileName() != null && getOutputFileName() != null) {
			LoggingUtils.logInfo(StudentAggregationModel.class, "Aggregation start.");
			LoggingUtils.logInfo(StudentAggregationModel.class, "Input File: "
					+ getInputFileName());
			LoggingUtils.logInfo(StudentAggregationModel.class, "Output File: "
					+ getOutputFileName());
			// try opening the source and destination file
			// with BufferedInputSteam and BufferedOutputStream
			// try-with-resources will automatically release FileReader object
			// these
			// two objects
			try (BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(getInputFileName()));
					BufferedOutputStream out = new BufferedOutputStream(
							new FileOutputStream(getOutputFileName()))) {
				getAggregationDriver().aggregateRecords(in, out);
			} catch (FileNotFoundException fnfe) {
				LoggingUtils.logError(fnfe,
						"Cannot open file: " + fnfe.getMessage());
			} catch (IOException ioe) {
				LoggingUtils.logError(ioe, ioe.getMessage());
			}
			LoggingUtils.logInfo(StudentAggregationModel.class, "Aggregation finish.");
		}
	}

}
