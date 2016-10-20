package demo.common.model;

import demo.common.driver.AggregationDriver;

public abstract class AbstractAggregationModel<T extends AggregationDriver>
		implements AggregationModel<AggregationDriver> {

	@Override
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	@Override
	public String getOutputFileName() {
		return outputFileName;
	}

	@Override
	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	@Override
	public String getInputFileName() {
		return inputFileName;
	}

	@Override
	public void setAggregationDriver(AggregationDriver driver) {
		this.aggregationDriver = driver;
	}

	@Override
	public AggregationDriver getAggregationDriver() {
		return aggregationDriver;
	}

	private AggregationDriver aggregationDriver;
	private String inputFileName;
	private String outputFileName;
}
