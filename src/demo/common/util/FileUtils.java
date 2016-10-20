package demo.common.util;

import java.io.File;

/**
 * A utility class to make file relevant function easier
 * 
 * @author richardl
 *
 */
public class FileUtils {
	
	/**
	 * Method used to create all parent directories for a file
	 * @param fileLocation The full path of a file
	 */
	public static void mkdirs(String fileLocation) {
		File file = new File(fileLocation);
		String filePath = file.getParent();
		File dir = new File(filePath);
		dir.mkdirs();
	}
	
	/**
	 * Get the absolute path of the application
	 * @return absolute path of the application
	 */
	public static String getAbsolutePath() {
		File file = new File("");
		return file.getAbsolutePath();
	}
}
