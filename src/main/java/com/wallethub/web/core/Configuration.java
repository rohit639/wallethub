package com.wallethub.web.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.wallethub.web.utils.testNG.TestNGParams;

/**
 * <p>
 * Main class to configure environment variables.
 * </p>
 * <p>
 * This set all the properties provided in Config file, TestNG xml and maven.
 * </p>
 * 
 * @author Rohit P Kumar
 *
 */
public class Configuration {

	private final static Properties prop = new Properties();

	static {
		try {
			prop.load(new FileInputStream(Constant.CONFIGRATION_FILE));
		} catch (IOException e) {
			LoggerClass.createLogger().error(e.toString());
		}
	}

	private final static String URL = setEnvrVariable("url");
	private final static String BROWSER = setEnvrVariable("browser");
	private final static Boolean RETRY_FAILED = Boolean.parseBoolean(setEnvrVariable("retryFailed"));
	private final static int MAX_RETRY_COUNT = Integer.parseInt(setEnvrVariable("maxRetryCount"));
	private final static String APP_NAME = getProperties("AppName");
	private final static int IMPLICIT = Integer.parseInt(getProperties("implicit"));
	private final static int PAGE_LOAD = Integer.parseInt(getProperties("pageLoad"));
	private final static int EXPLICIT = Integer.parseInt(getProperties("explicit"));
	private final static int SCRIPT = Integer.parseInt(getProperties("script"));
	private final static boolean LOCAL_SLOW = Boolean.parseBoolean(getProperties("localSlow"));
	private final static int TIME_TO_SLOW = Integer.parseInt(getProperties("timeToSlow"));
	private final static String USER_NAME = setEnvrVariable("userName");
	private final static String USER_PASSWORD = setEnvrVariable("userPassword");

	/**
	 * <p>
	 * Internal method to set variable from maven, testNG and Config file
	 * </p>
	 * 
	 * @param parameter takes the key to set value.
	 * @return value after setting and taking in order.
	 * 
	 */
	private static String setEnvrVariable(String parameter) {
		// try catch not required keeping for safe purpose.
		try {
			String testNGProp = TestNGParams.getTestNgParam(parameter);
			String mvnProp = System.getProperty(parameter);

			if (mvnProp != null && !mvnProp.isEmpty()) {
				return mvnProp;
			} else if (testNGProp != null && !testNGProp.isEmpty()) {
				return testNGProp;
			} else {
				return getProperties(parameter);
			}
		} catch (NullPointerException e) {
			LoggerClass.createLogger().error(
					"Failed to set environment properties for \"" + parameter + "\" due to:-\n" + e.getMessage());
			return getProperties(parameter);
		} catch (Exception e) {
			LoggerClass.createLogger().error(
					"Failed to set environment properties for \"" + parameter + "\" due to:-\n" + e.getMessage());
			return getProperties(parameter);
		}
	}

	public static String getUsername() {

		return setEnvrVariable("userName");
	}

	public static String getUserpassword() {
		return setEnvrVariable("userPassword");
	}

	public static int getTimetoslow() {
		return TIME_TO_SLOW;
	}

	public static String getbrowser() {
		return BROWSER;
	}

	public static int getScript() {
		return SCRIPT;
	}

	private final static String getProperties(String akey) {
		return prop.getProperty(akey);
	}

	public static String getUrl() {
		return setEnvrVariable("url");
	}

	public static int getImplicit() {
		return IMPLICIT;
	}

	public static int getPageload() {
		return PAGE_LOAD;
	}

	public static int getExplicit() {
		return EXPLICIT;
	}

	public static String getAppName() {
		return APP_NAME;
	}

	public static boolean isLocalslow() {
		return LOCAL_SLOW;
	}

	public static boolean isRetryFailedEnabled() {
		return RETRY_FAILED;
	}

	public static int getMaxRetryCount() {
		return MAX_RETRY_COUNT;
	}

	// Experimental method not a good way to get properties
	public static Map<String, Object> getAllFrameworkProperties() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("URL", getUrl());
		map.put("BROWSER", getbrowser());
		map.put("RETRY_FAILED", isRetryFailedEnabled());
		map.put("MAX_RETRY_COUNT", getMaxRetryCount());
		map.put("APP_NAME", getAppName());
		map.put("IMPLICIT", getImplicit());
		map.put("PAGE_LOAD", getPageload());
		map.put("EXPLICIT", getExplicit());
		map.put("SCRIPT", getScript());
		map.put("LOCAL_SLOW", isLocalslow());
		map.put("TIME_TO_SLOW", getTimetoslow());
		map.put("USER_NAME", getUsername());
		map.put("USER_PASSWORD", getUserpassword());
		return map;
	}

}
