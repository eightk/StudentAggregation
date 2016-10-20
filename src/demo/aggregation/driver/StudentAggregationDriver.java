package demo.aggregation.driver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Map;

import demo.common.driver.AbstractAggregationDriver;
import demo.common.log.LoggingUtils;
import demo.common.util.MapUtils;
import demo.common.util.ValidationUtils;

public class StudentAggregationDriver extends AbstractAggregationDriver {

	public static StudentAggregationDriver getDefault() {
		return new StudentAggregationDriver();
	}

	@Override
	public void aggregateRecords(InputStream in, OutputStream out)
			throws IOException {
		int ch = 0;// characters to read
		Map<String, Integer> provinceMapping = new Hashtable<String, Integer>();
		Map<String, Integer> cityMapping = new Hashtable<String, Integer>();
		Map<String, Integer> schoolMapping = new Hashtable<String, Integer>();
		Map<String, Integer> gradeMapping = new Hashtable<String, Integer>();

		StringBuffer sb = new StringBuffer();

		while (true) {
			if ((ch = in.read()) != -1 && ch != 13) {
				sb.append((char) ch);
			} else {
				String rowValue = sb.toString();
				String[] rowParts = rowValue.split("\t");
				LoggingUtils.logInfo(StudentAggregationDriver.class, "Input value: '" + rowValue.trim() + "' with " + rowParts.length + " sections.");
				if (rowParts.length != 4) {
					LoggingUtils.logInfo(StudentAggregationDriver.class,
							"Illegal Input value: '" + rowValue.trim()
									+ "' Should have 4 sections.");
				} else {
					String pattern = "(^[k-kK-K1-8]{1})(\\d{4})$";
					if (!ValidationUtils.matchPattern(rowParts[3], pattern)) {
						LoggingUtils
								.logInfo(
										StudentAggregationDriver.class,
										"Last section '"
												+ rowParts[3]
												+ "' does not have proper format:\n the length should be exactly 5, "
												+ "the first letter should be either 'k' or 1-8 "
												+ "and the next 4 digits should all be number.");
						sb = new StringBuffer();
						continue;
					}
					//LoggingUtils.logInfo(StudentAggregationDriver.class, "Input value: " + rowValue);
					String grade = rowParts[3].substring(0, 1);
					// Make sure both "k" and "K" considered to be the same grade
					if ("k".equals(grade)) {
						grade = "K";
					}
					Integer value;
					try {
						value = Integer.parseInt(rowParts[3].substring(1));
					} catch (NumberFormatException ex) {
						LoggingUtils.logError(ex, "Failed to parse string "
								+ rowParts[3].substring(1) + " into number");
						sb = new StringBuffer();
						continue;
					}

					if (value != null) {
						provinceMapping = MapUtils
								.addRecord(provinceMapping, rowParts[0].trim()
										.isEmpty() ? "Unknown Province"
										: rowParts[0].trim(), value);
						cityMapping = MapUtils.addRecord(cityMapping,
								rowParts[1].trim().isEmpty() ? "Unknown City"
										: rowParts[1].trim(), value);
						schoolMapping = MapUtils.addRecord(schoolMapping,
								rowParts[2].trim().isEmpty() ? "Unknown School"
										: rowParts[2].trim(), value);
						gradeMapping = MapUtils.addRecord(gradeMapping, grade,
								value);
					}
				}
				sb = new StringBuffer();
			}
			//Have to stop the loop after the last
			if(ch==-1) {
				break;
			}
		}
		LoggingUtils.logInfo(StudentAggregationDriver.class,
				"Generating result file.");
		MapUtils.writeResult(provinceMapping, out);
		MapUtils.writeResult(cityMapping, out);
		MapUtils.writeResult(schoolMapping, out);
		MapUtils.writeResult(gradeMapping, out);
	}

}
