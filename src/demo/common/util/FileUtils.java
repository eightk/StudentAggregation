package demo.common.util;

import java.io.File;

public class FileUtils {
	public static void mkdirs(String fileLocation) {
		File file = new File(fileLocation);
		String filePath = file.getParent();
		File dir = new File(filePath);
		dir.mkdirs();
	}
	
	public static String getAbsolutePath() {
		File file = new File("");
		return file.getAbsolutePath();
	}
}
