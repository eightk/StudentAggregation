package demo.aggregation.driver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Map;

import demo.common.util.MapUtils;
import demo.common.util.ValidationUtils;

public class StudentAggregationDriver extends AbstractAggregationDriver {

    public static StudentAggregationDriver getDefault() {
        return new StudentAggregationDriver();
    }
    
    @Override
    public void aggregateRecords(InputStream in, OutputStream out) throws IOException {
        int ch = 0;//characters to read
        Map<String, Integer> provinceMapping = new Hashtable();
        Map<String, Integer> cityMapping = new Hashtable();
        Map<String, Integer> schoolMapping = new Hashtable();
        Map<String, Integer> gradeMapping = new Hashtable();

        StringBuffer sb = new StringBuffer();
        
        while ((ch = in.read()) != -1) {
            if (ch != 13) {
                sb.append((char) ch);
            } else {
                String rowValue = sb.toString();
                System.out.println(rowValue);
                String[] rowParts = rowValue.split("\t");
                if (rowParts.length != 4) {
                    //Add Log
                    System.err.println("Wrong row record: " + rowValue);
                } else {
                    String pattern = "(^[k-kK-K1-8]{1})(\\d{4})$";
                    if (!ValidationUtils.matchPattern(rowParts[3], pattern)) {
                        //If the last part of the record does not fit the format
                        //Add Log
                        break;
                    }
                    String grade = rowParts[3].substring(0, 1);
                    if ("k".equals(grade)) {//Make sure both "k" and "K" considered to be the same grade 
                        grade = "K";
                    }
                    Integer value;
                    try {
                        value = Integer.parseInt(rowParts[3].substring(1));
                    } catch (NumberFormatException ex) {
                        //Add Log
                        System.err.println("Failed to get the number of the record: " + rowValue);
                        break;
                    }

                    if (value != null) {
                        provinceMapping = MapUtils.addRecord(provinceMapping, rowParts[0], value);
                        cityMapping = MapUtils.addRecord(cityMapping, rowParts[1], value);
                        schoolMapping = MapUtils.addRecord(schoolMapping, rowParts[2], value);
                        gradeMapping = MapUtils.addRecord(gradeMapping, grade, value);
                    }
                }
                sb = new StringBuffer();
            }
            //outputFile.write((char) ch);
        }
        MapUtils.writeResult(provinceMapping, out);
        MapUtils.writeResult(cityMapping, out);
        MapUtils.writeResult(schoolMapping, out);
        MapUtils.writeResult(gradeMapping, out);
        // no need to call flush explicitly for outputFile - the close()
        // method will first call flush before closing the outputFile stream
    }

}
