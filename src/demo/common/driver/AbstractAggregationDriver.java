package demo.common.driver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * The abstract class for AggregationDriver
 * 
 * @author richardl
 *
 */
public abstract class AbstractAggregationDriver implements AggregationDriver {
	@Override
    public abstract Map<String, Map> aggregateRecords(InputStream in) throws IOException;
	
	@Override
	public abstract void generateResultFile(Map<String, Map> resultMaps, OutputStream out)throws IOException ;
}
