package demo.aggregation.driver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface AggregationDriver {
	public abstract void aggregateRecords(InputStream in, OutputStream out) throws IOException;
}