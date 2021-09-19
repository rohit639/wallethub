package com.wallethub.web.core.reports;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.wallethub.web.core.Configuration;

import io.qameta.allure.Attachment;

/**
 * <p>
 * All setup related and customization with configuring Allure report.
 * </p>
 * 
 * @author Rohit P Kumar
 *
 */
public class AllureReport {

	/**
	 * <p>
	 * Set the environment properties for allure report
	 * </p>
	 * 
	 */
	public static void setAllureEnvironment() {
		Properties prop = new Properties();
		try {
			File file = new File(System.getProperty("user.dir") + "/allure-results/environment.properties");
			if (!file.exists())
				file.createNewFile();
			prop.setProperty("Base URL", Configuration.getUrl());
			prop.setProperty("Browser", Configuration.getbrowser());
			prop.setProperty("Time Zone", System.getProperty("user.timezone"));
			prop.setProperty("Java Version", System.getProperty("java.version"));
			prop.store(new FileOutputStream("allure-results/" + "environment.properties", true),
					"This will help to save during test execution");

		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * <p>
	 * Takes the failure screenshot and save it to allure report
	 * </p>
	 * 
	 * @param failedScreenshot TakesScreenshot interface to capture in byte
	 * @return captured screenshot in byte
	 */
	@Attachment(value = "Failure Screenshot", type = "image/png")
	public static byte[] takeScreenshot(TakesScreenshot failedScreenshot) {
		// Take a screenshot as byte array and return
		return failedScreenshot.getScreenshotAs(OutputType.BYTES);
	}

	/**
	 * <p>
	 * Takes the failure logs and save it to allure report
	 * </p>
	 * 
	 * @param exception failure log message which needs to be saved in Allure report
	 * @return captured logs in simple plain text as string
	 */
	@Attachment(value = "Failure Log", type = "text/plain")
	public static String takelogs(String exception) {

		return exception;
	}

}
