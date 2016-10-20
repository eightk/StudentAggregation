package demo.common.driver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
/**
 * The AggregationDriver interface
 * 
 * @author richardl
 *
 */
public interface AggregationDriver {
	
	public abstract Map<String, Map> aggregateRecords(InputStream in) throws IOException;
	
	public abstract void generateResultFile(Map<String, Map> resultMaps, OutputStream out)throws IOException ;
	
}
