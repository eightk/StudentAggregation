package demo.common.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

public class MapUtils {
	public static Map<String, Integer> addRecord(Map<String, Integer> map, String key, Integer value) {
        if (map.containsKey(key.trim())) {
            Integer newValue = map.get(key.trim()) + value;
            map.put(key.trim(), newValue);
        } else {
            map.put(key.trim(), value);
        }
        return map;
    }

    public static void writeResult(Map<String, Integer> map, OutputStream outputFile) throws IOException {
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();

            outputFile.write(entry.getKey().getBytes());
            outputFile.write(9);
            outputFile.write(entry.getValue().toString().getBytes());
            outputFile.write(13);
        }
    }
}
