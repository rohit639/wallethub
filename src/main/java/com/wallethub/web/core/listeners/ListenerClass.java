package com.wallethub.web.core.listeners;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.Status;
import com.wallethub.web.core.BaseWebdriver;
import com.wallethub.web.core.Configuration;
import com.wallethub.web.core.LoggerClass;
import com.wallethub.web.core.reports.AllureReport;
import com.wallethub.web.core.reports.ExtendedReport;
import com.wallethub.web.utils.RuntimeUtils;
import com.wallethub.web.utils.testNG.TestNGParams;
import com.wallethub.web.utils.webdriver.CommonUtil;

/**
 * <p>
 * Implementation of listener interface provided by various tools.
 * </p>
 * <p>
 * Such as:- TestNG, WebDriver and etc
 * </p>
 * 
 * @author Rohit P Kumar
 *
 */
public class ListenerClass implements WebDriverEventListener, ITestListener, IReporter, ISuiteListener {

	public static final Logger logger = LoggerClass.createLogger();

	public void afterAlertAccept(WebDriver arg0) {
		logger.debug("Successfully accepted alert...");

	}

	public void afterAlertDismiss(WebDriver arg0) {
		logger.debug("Successfully dismissed alert...");

	}

	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		try {
			if (arg0.getAttribute("value") != null && !(arg0.getAttribute("value").isEmpty()))
				logger.debug("Successfully sendKeys  \"" + arg0.getAttribute("value") + "\" to web element : "
						+ arg0.toString());
			else
				logger.debug("Successfully cleared value from web element :" + arg0.toString());
		} catch (StaleElementReferenceException e) {
			logger.warn("The element is no longer present in DOM " + e);
		}

	}

	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		logger.debug("Successfully clicked on element :" + arg0.toString());
		CommonUtil.javaScriptToWaitForPage();

	}

	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
	}

	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void afterNavigateTo(String arg0, WebDriver arg1) {
		logger.debug("Navigated to page : " + arg0.trim());

	}

	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	public void beforeAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void beforeAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		CommonUtil.scrolIntoElement(arg0);
		if (arg0.getText() != null && !(arg0.getText().isEmpty())) {
			logger.debug("Before using sendKeys/clear the value of element is :" + arg0.getAttribute("value"));
		}
		performOnLocalSlowEnabled(arg0);
	}

	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		CommonUtil.javaScriptToWaitForPage();
		CommonUtil.scrolIntoElement(arg0);
		logger.debug("trying  to click on element with locator as  : " + arg0.toString());
		if (arg0.getText() != null && !(arg0.getText().isEmpty())) {
			logger.debug("About to click on element " + arg0.getText());
		}

	}

	private void performOnLocalSlowEnabled(WebElement arg0) {
		if (Configuration.isLocalslow() && System.getProperty("environment").equalsIgnoreCase("local")) {
			com.wallethub.web.utils.CommonUtil.slowHighlightElement(arg0);
		}
	}

	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		logger.debug(("Trying to find Element :-> " + arg0.toString()));

	}

	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		logger.debug("About to navigate page :" + arg0.trim());

	}

	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {

	}

	public void onException(Throwable arg0, WebDriver arg1) {
		String line = new String(new char[100]).replace('\0', '-');
		logger.error("Found exception for test case :-> \"" + Reporter.getCurrentTestResult().getName() + "\"\n" + line
				+ line, arg0);
		logger.info("\n" + line + line + "\n" + line + line + "\n\n");

	}

	public void onTestStart(ITestResult result) {

		if (!RetryFailTest.retryCase) {
			String line = new String(new char[130]).replace('\0', '-');
			logger.info("\n");
			logger.info(line);
			logger.info(StringUtils.center("Test case :-->  \"" + result.getName() + " ("
					+ result.getMethod().getDescription() + ")\"  <--: about to start...", 130));
			logger.info(line + "\n\n");
			ExtendedReport.startTest(result.getName(), result.getMethod().getDescription());
		}

	}

	public void onTestSuccess(ITestResult result) {
		ExtendedReport.test.log(Status.PASS, "**passed**");
		String line = new String(new char[120]).replace('\0', '-');
		logger.info(line);
		logger.info(StringUtils.center(String.format("|%30s|", result.getTestClass())
				+ String.format("|%20s|", result.getName()) + String.format("|%10S|", "passed"), 120));
		logger.info(line + "\n");

	}

	public void onTestFailure(ITestResult result) {
		String screenShotFilePath = CommonUtil.createScreenshot(BaseWebdriver.getDriver(), result.getName());
		Path resourceDirectory;
		resourceDirectory = Paths.get("logs", screenShotFilePath);
		AllureReport.takelogs(result.getThrowable().toString());
		try {
			ExtendedReport.failLogWithScreenshots(result.getThrowable().toString(), resourceDirectory.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = new String(new char[120]).replace('\0', '-');
		logger.info(line);
		logger.info(StringUtils.center(String.format("|%30s|", result.getTestClass())
				+ String.format("|%20s|", result.getName()) + String.format("|%10S|", "failed"), 120));
		logger.info(line + "\n");
		logger.error("Failed due to :-> \n", result.getThrowable());
		logger.info("\n" + line + line + "\n" + line + line + "\n\n");

	}

	public void onTestSkipped(ITestResult result) {
		if (!RetryFailTest.retryCase) {
			String line = new String(new char[120]).replace('\0', '-');
			logger.info(line);
			logger.info(StringUtils.center(String.format("|%30s|", result.getTestClass())
					+ String.format("|%20s|", result.getName()) + String.format("|%10S|", "skipped"), 120));
			logger.info(line + "\n");
			logger.error("Skipped due to :-> \n", result.getThrowable());
			logger.info("\n" + "\n" + line + "\n\n");
			ExtendedReport.SkipLog(result.getThrowable().toString());
		}

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		RetryFailTest retry = new RetryFailTest();
		for (ITestNGMethod method : context.getAllTestMethods()) {
			method.setRetryAnalyzer(retry);
		}

	}

	public void onFinish(ITestContext context) {
		ExtendedReport.extent.flush();
		String line = new String(new char[120]).replace('\0', '-');
		int pass = context.getPassedTests().size();
		int fail = context.getFailedTests().size();
		int skip = context.getSkippedTests().size();
		int total = pass + fail + skip;
		logger.info(line);
		logger.info(StringUtils.center("|Total Number of Test cases Executed for suite \""
				+ context.getCurrentXmlTest().getName() + "\" ||" + " " + total + " ||", 120));
		logger.info(line);
		logger.info(StringUtils.center(String.format("|%5S|", "Total Passed") + String.format("|%5s|", pass)
				+ String.format("|%5S|", "Total Failed") + String.format("|%5s|", fail)
				+ String.format("|%5S|", "Total skipped") + String.format("|%5s|", skip), 120));
		logger.info(line + "\n");

	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
		logger.debug("Successfully got text as :-> \"" + arg2 + "\" from web element " + arg0.toString());

	}

	@Override
	public void beforeGetText(WebElement arg0, WebDriver arg1) {
		logger.debug("Trying to get text from web element " + arg0.toString());
		performOnLocalSlowEnabled(arg0);

	}

	/// ------Report creation --------

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

		try {
			// need to run only if no configuration failure needs to work on this
			if (Configuration.isLocalslow() && System.getProperty("environment").equalsIgnoreCase("local"))
				ExtendedReport.openGenratedReports();
			RuntimeUtils.copyGenratedProperties();
			String line = new String(new char[20]).replace('\0', '-');
			logger.info(line + "-> Framework Properties used are <-" + line);
			logger.info(com.wallethub.web.utils.CommonUtil.mapToString(Configuration.getAllFrameworkProperties()));
			logger.info(line + "-> Framework Properties used are  <-" + line);
			if (Configuration.isLocalslow())
				logger.warn(
						"local slow is enabled/true, please make sure not to commit & push it with true as in build environment it will make scripts run slow, Very Slow.");
			AllureReport.setAllureEnvironment();
		} catch (Exception ex) {

			ex.printStackTrace();
		}
	}

	@Override
	public void onStart(ISuite suite) {

		TestNGParams.setSuiteParametrs(suite.getXmlSuite().getAllParameters());
	}

	@Override
	public void onFinish(ISuite suite) {

	}

}
