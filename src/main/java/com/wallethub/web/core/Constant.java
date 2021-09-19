package com.wallethub.web.core;

import java.time.LocalDate;

/**
 * <p>
 * A common constant class for all the constants related to framework
 * </p>
 * <p>
 * All members of this class are immutable and all constant related to
 * configuring web automation.
 * </p>
 * 
 * @author Rohit P Kumar
 *
 */
public class Constant {

	/** Location of logs file */
	public static final String REPORT_LOCATION = "logs/";
	/** Location of image files */
	public static final String IMAGE_LOCATION = "images/";
	/** Format for date and time */
	public static final String DATE_TIME_FORMAT = "MM-dd-yyyy_hh.mm.ss";
	/** Location of log4j property */
	public static final String LOG_PROPERTY_FILE = "/src/main/resources/log4j.properties";
	/** Location of framework configuration file */
	public static final String CONFIGRATION_FILE = "config.properties";
	/** URL Location of Wallethub logo */
	public final static String LOGO_URL = "https://www.emorybusiness.com/wp-content/uploads/2020/09/WalletHub_logo._smallpng-300x194.png";

}
