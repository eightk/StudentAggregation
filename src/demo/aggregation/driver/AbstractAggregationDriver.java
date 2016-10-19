package demo.aggregation.driver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractAggregationDriver implements AggregationDriver {
	@Override
    public abstract void aggregateRecords(InputStream in, OutputStream out) throws IOException;
}
