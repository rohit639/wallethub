package com.wallethub.web.core.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.wallethub.web.core.Configuration;
import com.wallethub.web.core.Constant;

/**
 * <p>
 * All setup and customization related with configuring extent report.
 * </p>
 * 
 * @author Rohit P Kumar
 *
 */
public class ExtendedReport {

	private static final ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
			new File(Configuration.getAppName() + ".html"));
	public static final ExtentReports extent = new ExtentReports();
	public static ExtentTest test;

	static {

		String js = "$('.brand-logo').text('').append('<img src=" + Constant.LOGO_URL
				+ " width=\"62px;\" height=\"50px;\""
				+ " style=\" margin-bottom: -10px; margin-left: -12px; \" alt=\"Wallethub\">')";
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/report-config.xml"));
		htmlReporter.config().setDocumentTitle(Configuration.getAppName() + "-Automation Report");
		htmlReporter.config().setReportName(Configuration.getAppName() + " -- Test Cases Execution Result");
		htmlReporter.config().setJS(js);
		htmlReporter.setAppendExisting(false);
		extent.attachReporter(htmlReporter);

	}

	/**
	 * <p>
	 * Add test case name to report with its description
	 * </p>
	 *
	 * @param testName       The test case name
	 * @param testDescripton description of test case
	 */
	public static void startTest(String testName, String testDescripton) {
		test = ExtendedReport.extent.createTest(testName, testDescripton);
	}

	/**
	 * <p>
	 * Add failed log message to report with captured screenshots
	 * </p>
	 *
	 * @param errorMessage The error log
	 * @param filePath     the path of screenshots saved
	 * @throws IOException if screenshots path is wrong
	 */
	public static void failLogWithScreenshots(String errorMessage, String filePath) throws IOException {
		ExtendedReport.test.log(Status.FAIL, "Failed due to:-  " + errorMessage);
		ExtendedReport.test.fail("*ScreenShot*", MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
	}

	public static void SkipLog(String errorMessage) {
		ExtendedReport.test.log(Status.SKIP, "Skipped due to :-  " + errorMessage);
	}

	/**
	 * <p>
	 * Auto open the report in default browser.
	 * </p>
	 * <p>
	 * IOException Report html file will be only generated once whole execution end,
	 * calling this method before will throw this Exception
	 * </p>
	 */
	public static void openGenratedReports() {
		final File file = new File(Configuration.getAppName() + ".html");
		if (System.getProperty("environment").equalsIgnoreCase("local")) {
			try {
				Desktop.getDesktop().browse(file.toURI());
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
