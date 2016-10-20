package demo.common.model;

import java.io.IOException;

import demo.common.driver.AggregationDriver;

public interface AggregationModel<T extends AggregationDriver> {
	public void setInputFileName(String inputFileName);

	public String getInputFileName();

	public void setOutputFileName(String outputFileName);

	public String getOutputFileName();

	public void setAggregationDriver(T driver);

	public T getAggregationDriver();

	public void aggregate();
}
