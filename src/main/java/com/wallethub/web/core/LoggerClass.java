package com.wallethub.web.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * <p>
 * Logger class use to manipulate log file on the go.
 * </p>
 * <p>
 * This is internal class, no edit should be done.
 * </p>
 * 
 * @author Rohit P Kumar
 *
 */
public class LoggerClass {

	private static Logger logger;

	private final static Properties prop = new Properties();

	static {

		try {
			prop.load(new FileInputStream("config.properties"));
			String dateTime = getFormattedCurrentDateAndTime(Constant.DATE_TIME_FORMAT);
			String FileName = prop.getProperty("AppName") + dateTime + ".log";
			Properties props = new Properties();
			System.out.println(System.getProperty("user.dir"));
			props.load(new FileInputStream(System.getProperty("user.dir") + Constant.LOG_PROPERTY_FILE));
			props.setProperty("log4j.appender.R.File", "logs/" + FileName);
			LogManager.resetConfiguration();
			PropertyConfigurator.configure(props);
			System.out.println("Property log4j.appender.R.File = logs/" + FileName);

		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.print("IO Exception in static method of Logger Class. " + ex.getMessage());
			System.exit(-1);
		}

	}

	/**
	 * The only method to be called outside so, logs could be generated
	 * 
	 * @return customized logger after setting logs based on framework changes
	 *         required.
	 */
	public static Logger createLogger() {
		if (logger == null) {
			logger = LogManager.getLogger(LoggerClass.class);
			logger.setLevel(Level.ALL);

			return logger;
		} else
			return logger;
	}

	private static String getFormattedCurrentDateAndTime(String format) {
		String formattedDateTime = null;
		try {

			formattedDateTime = new SimpleDateFormat(format).format(new Date());

		} catch (Exception ex) {
			return null;
		}
		return formattedDateTime;
	}

}
