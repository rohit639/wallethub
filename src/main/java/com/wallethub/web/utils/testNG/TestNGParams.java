package com.wallethub.web.utils.testNG;

import java.util.Map;

/**
 * <p>
 * Pojo for testNG parameters from xml files
 * </p>
 * 
 * @author Rohit P Kumar
 *
 */
public class TestNGParams {

	private static Map<String, String> allParameters;

	/**
	 * <p>
	 * Set TestNG suites parameter to Map
	 * </p>
	 *
	 * @param suiteParams get all suite parameter
	 * 
	 */
	public static void setSuiteParametrs(Map<String, String> suiteParams) {
		TestNGParams.allParameters = suiteParams;
	}

	/**
	 * <p>
	 * Get TestNG suites parameters
	 * </p>
	 *
	 * @return maps with all suite parameter else return null
	 */
	public static Map<String, String> getAllTestNgParams() {
		try {
			return allParameters;
		} catch (NullPointerException e) {
			// logger for error as its not assigned
			return null;
		}

	}

	/**
	 * <p>
	 * get TestNG suites parameter value
	 * </p>
	 *
	 * @param akey unique key set in testNG xml file
	 * @return value of provided key in parameter
	 */
	public static String getTestNgParam(String akey) {
		return allParameters.get(akey);

		// try catch and error message for exceptions
	}

}
