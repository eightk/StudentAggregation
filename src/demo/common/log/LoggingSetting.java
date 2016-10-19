package demo.common.log;

import org.apache.log4j.Level;

public class LoggingSetting {
	public static LoggingSetting getDefault() {
        LoggingSetting setting = new LoggingSetting();
        setting.setPattern("%d{ISO8601} [%c] %p - %m%n");
        setting.setLoggingLevel(Level.INFO);
        setting.setFileName("Demo.log");
        setting.setMaxFileSize(10);
        return setting;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Level getLoggingLevel() {
        return loggingLevel;
    }

    public void setLoggingLevel(Level loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public Integer getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(Integer maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    private String pattern;
    private Level loggingLevel;
    private String fileName;
    private Integer maxFileSize;
}
