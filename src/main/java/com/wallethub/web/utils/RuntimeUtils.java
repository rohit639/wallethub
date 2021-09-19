package com.wallethub.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.wallethub.web.core.Configuration;
import com.wallethub.web.core.LoggerClass;

/**
 * <p>
 * Implementation of saving and getting runtime arguments
 * </p>
 * 
 * @author Rohit P Kumar
 *
 */
public class RuntimeUtils {

	private static final Logger logger = LoggerClass.createLogger();

	/**
	 * <p>
	 * save arguments at runtime. use property to save.
	 * </p>
	 *
	 * @param key   the unique key with values needs to be saved
	 * @param value associated value with key
	 */
	public static void saveAtRunTime(String key, String value) {
		Properties prop = new Properties();
		try {
			prop.setProperty(key, value);
			logger.debug("Saving the value " + value + ", during test execution with key  as : " + key);
			prop.store(
					new FileOutputStream("./src/main/resources/" + Configuration.getAppName() + "Generated.properties",
							true),
					"This will help to save during test execution");

		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * <p>
	 * get arguments at runtime.
	 * </p>
	 *
	 * @param key the unique key for which values needs to be retrieved
	 * @return associated value with key
	 */
	public static String getAtRunTime(String key) {
		String value = null;
		Properties prop = new Properties();
		try {
			prop.load(
					new FileInputStream("./src/main/resources/" + Configuration.getAppName() + "Generated.properties"));
			if (key.isEmpty() || key.equalsIgnoreCase(null)) {
				logger.debug("Failed to get the value during test execution as key is empty or null");

			} else {
				value = prop.getProperty(key);
			}
			logger.debug("fetched the value of key (" + key + ") at run time as : " + value);
			return value;
		} catch (FileNotFoundException e) {
			logger.error("Could not find file for the provided key, which is :" + key);
			return null;
		} catch (Exception e) {
			logger.error("Could not find value for the provided key, which is :" + key);
			return null;
		}

	}

	public static void copyGenratedProperties() {
		final File genratedPropetiesAtRunTime = new File(
				"./src/main/resources/" + Configuration.getAppName() + "Generated.properties");
		final String fileCopyLocation = "./logs/propertiesFile/" + Configuration.getAppName() + "_"
				+ RandomData.getToDayDate("dd-MMM-yy_HH-mm-ss");
		try {
			if (genratedPropetiesAtRunTime.exists()) {
				FileUtils.copyFile(genratedPropetiesAtRunTime, new File(fileCopyLocation + ".properties"));
				logger.debug("Successfully copied run time generated properties file for this suite to location "
						+ fileCopyLocation);
				FileUtils.forceDelete(genratedPropetiesAtRunTime);
				logger.debug("Successfully deleted run time generated properties file for this suite from location "
						+ genratedPropetiesAtRunTime);
			}
		} catch (final IOException e) {
			logger.debug("Could not find file at location " + genratedPropetiesAtRunTime + " to copy file");
			e.printStackTrace();
		}
	}

}
