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

/**
 * Method for handling Input as it is read in and printing output based on Input
 * results. The Logic based on the following assumptions: 1. every single row
 * should have 4 sections: province, city, school and grade+number 2.
 * grade+number format by default should be: the first letter should be either
 * 'k' or 1-8, the next 4 digits should all be number and total length is 5. Can
 * set the pattern to change the format. 3. output file content can be
 * controlled by different variables, by default, we only print province, city
 * and school.
 * 
 * 
 * @author $Author: richardl $
 */
public class StudentAggregationDriver extends AbstractAggregationDriver {

	private StudentAggregationDriver() {
	}
	
	public static StudentAggregationDriver getDefault() {
		return new StudentAggregationDriver();
	}

	/**
	 * Read in the value from input stream: can be File, Database, String or any
	 * other possible input streams. Handle the information as it is read in.
	 * 
	 * @return a Map object contains all 4 aggregation results for province,
	 *         city, school and grade.
	 */
	@Override
	public Map<String, Map> aggregateRecords(InputStream in) throws IOException {
		int ch = 0;// characters to read
		Map<String, Map> resultMaps = new Hashtable<String, Map>();
		Map<String, Integer> provinceMapping = new Hashtable<String, Integer>();
		Map<String, Integer> cityMapping = new Hashtable<String, Integer>();
		Map<String, Integer> schoolMapping = new Hashtable<String, Integer>();
		Map<String, Integer> gradeMapping = new Hashtable<String, Integer>();
		resultMaps.put("province", provinceMapping);
		resultMaps.put("city", cityMapping);
		resultMaps.put("school", schoolMapping);
		resultMaps.put("grade", gradeMapping);

		StringBuffer sb = new StringBuffer();

		while (true) {
			if ((ch = in.read()) != -1 && ch != 13) {
				sb.append((char) ch);
			} else {
				String rowValue = sb.toString();
				String[] rowParts = rowValue.split("\t");
				LoggingUtils.logInfo(StudentAggregationDriver.class,
						"Input value: '" + rowValue.trim() + "' with "
								+ rowParts.length + " sections.");
				if (rowParts.length != 4) {
					// Check whether it is an empty line first
					if (rowValue.trim().isEmpty()) {
						if (ch == 13) {
							// if it is empty line then we skip it and continue
							// the progress
							LoggingUtils.logInfo(StudentAggregationDriver.class, "Skip a empty line.");
							continue;
						} else if (ch == -1) {
							// if the empty line is at the end of the file, we
							// break the loop
							LoggingUtils.logInfo(StudentAggregationDriver.class, "End of the file.");
							break;
						}
					} else {
						// if it is illegal value then we return null
						LoggingUtils.logError(StudentAggregationDriver.class,
								"Illegal Input value: '" + rowValue.trim()
										+ "' Should have 4 sections.");
						return null;
					}
				} else {
					if (!ValidationUtils.matchPattern(rowParts[3], pattern)) {
						LoggingUtils.logError(StudentAggregationDriver.class,
								"Last section '" + rowParts[3]
										+ "' does not have proper format");
						sb = new StringBuffer();
						return null;
					}
					// LoggingUtils.logInfo(StudentAggregationDriver.class,
					// "Input value: " + rowValue);
					String grade = rowParts[3].substring(0, 1);
					// Make sure both "k" and "K" considered to be the same
					// grade
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
						return null;
					}

					LoggingUtils.logInfo(StudentAggregationDriver.class,
							"Put the student number " + value
									+ " into the result sets.");
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
			// Have to stop the loop after the last
			if (ch == -1) {
				break;
			}
		}
		return resultMaps;
	}

	/**
	 * Method for printing the result to the output stream. The assumption is
	 * the resultMaps should be the one generated by aggregateRecords method
	 */
	@Override
	public void generateResultFile(Map<String, Map> resultMaps, OutputStream out)
			throws IOException {
		LoggingUtils.logInfo(StudentAggregationDriver.class,
				"Generating result file.");
		if (getPrintProvince()) {
			MapUtils.writeResult(resultMaps.get("province"), out);
		}
		if (getPrintCity()) {
			MapUtils.writeResult(resultMaps.get("city"), out);
		}
		if (getPrintSchool()) {
			MapUtils.writeResult(resultMaps.get("school"), out);
		}
		if (getPrintGrade()) {
			MapUtils.writeResult(resultMaps.get("grade"), out);
		}
	}

	/* Pattern of the grade+number section for input stream */
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/* Variable for controlling whether should print city result or not */
	public Boolean getPrintCity() {
		return printCity;
	}

	public void setPrintCity(Boolean printCity) {
		this.printCity = printCity;
	}

	/* Variable for controlling whether should print grade result or not */
	public Boolean getPrintGrade() {
		return printGrade;
	}

	public void setPrintGrade(Boolean printGrade) {
		this.printGrade = printGrade;
	}

	/* Variable for controlling whether should print province result or not */
	public Boolean getPrintProvince() {
		return printProvince;
	}

	public void setPrintProvince(Boolean printProvince) {
		this.printProvince = printProvince;
	}

	/* Variable for controlling whether should print school result or not */
	public Boolean getPrintSchool() {
		return printSchool;
	}

	public void setPrintSchool(Boolean printSchool) {
		this.printSchool = printSchool;
	}

	private String pattern = "(^[k-kK-K1-8]{1})(\\d{4})$";
	private Boolean printProvince = Boolean.TRUE;
	private Boolean printCity = Boolean.TRUE;
	private Boolean printSchool = Boolean.TRUE;
	private Boolean printGrade = Boolean.FALSE;
	private Map<String, Map> resultMaps = new Hashtable<String, Map>();
}
