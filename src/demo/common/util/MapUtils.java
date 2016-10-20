package demo.common.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * A utility class to make Map object relevant function easier
 * 
 * @author richardl
 *
 */
public class MapUtils {
	
	/**
	 * if the key exist then update the value, else insert the key and value to the map
	 * @param map
	 * @param key
	 * @param value
	 * @return the updated map
	 */
	public static Map<String, Integer> addRecord(Map<String, Integer> map, String key, Integer value) {
        if (map.containsKey(key)) {
            Integer newValue = map.get(key) + value;
            map.put(key, newValue);
        } else {
            map.put(key, value);
        }
        return map;
    }

	/**
	 * Write the Map value to the output stream
	 * @param map
	 * @param output
	 * @throws IOException
	 */
    public static void writeResult(Map<String, Integer> map, OutputStream output) throws IOException {
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();

            output.write(entry.getKey().getBytes());
            output.write(9);
            output.write(entry.getValue().toString().getBytes());
            output.write(13);
        }
    }
}
