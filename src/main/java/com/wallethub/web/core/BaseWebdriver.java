package com.wallethub.web.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.wallethub.web.core.listeners.ListenerClass;
import com.wallethub.web.utils.webdriver.CommonUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * <p>
 * Main class to initialize web driver.
 * </p>
 * <p>
 * All cases related to configuring web automation are done in this class.
 * </p>
 * 
 * @author Rohit P Kumar
 *
 */
@Listeners(com.wallethub.web.core.listeners.ListenerClass.class)
public abstract class BaseWebdriver {

	public static WebDriver driver;
	private static final Logger logger = LoggerClass.createLogger();

	/**
	 * <p>
	 * This method is to do any setup before any suites start execution.
	 * </p>
	 * <p>
	 * TestNG method annotation alwaysRun is set to true
	 * </p>
	 */
	@BeforeSuite(alwaysRun = true)
	protected void initializeSuite() {
		if (Configuration.isLocalslow())
			logger.warn(
					"local slow is enabled/true, please make sure not to commit & push it with true as in build environment it will make scripts run slow, Very Slow.");
	}

	/**
	 * <p>
	 * This method is to do any setup before any test start execution.
	 * </p>
	 * <p>
	 * This take care of initializing Chrome/Firefox/Mobile_view
	 * </p>
	 * <p>
	 * TestNG method annotation alwaysRun is set to true
	 * </p>
	 * 
	 * @throws MalformedURLException
	 */
	@BeforeMethod(alwaysRun = true)
	protected void initTest(ITestResult result) throws MalformedURLException {
		if (driver != null) {
			driver = null;
		}
		browserSetup();
		BaseWebdriver.driver = registerEvents(driver);
		browserEnvironmentSetup();
	}

	/**
	 * <p>
	 * This method is to register listener class with WebDriver object
	 * </p>
	 * 
	 * @param driver1 takes WebDriver objects
	 * @return registered WebDriver with listener class
	 */
	private WebDriver registerEvents(WebDriver driver1) {
		EventFiringWebDriver edriver = new EventFiringWebDriver(driver1);
		ListenerClass listner = new ListenerClass();
		edriver.register(listner);
		return edriver;
	}

	/**
	 * <p>
	 * This method is to do any teardown after any test complete execution.
	 * </p>
	 * <p>
	 * End the WebDriver session.
	 * <p>
	 * TestNG method annotation alwaysRun is set to true
	 * </p>
	 */
	@AfterMethod(alwaysRun = true)
	public static void closeBrowser() {
		logger.info("Closing the session.");
		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * <p>
	 * This method is to do any teardown after any Suites complete execution.
	 * </p>
	 * <p>
	 * End the WebDriver session.
	 * <p>
	 * TestNG method annotation alwaysRun is set to true
	 * </p>
	 */
	@AfterSuite(alwaysRun = true)
	public static void closeWebDriver() {
		// needs to have workaround for safely close firefox
		if (driver != null & !Configuration.getbrowser().equalsIgnoreCase("firefox"))
			driver.quit();
	}

	/**
	 * <p>
	 * Return initialized object of web driver
	 * </p>
	 * 
	 * @return driver with all parameter setup to run it
	 */
	public static WebDriver getDriver() {
		return driver;
	}

	/**
	 * <p>
	 * Common environment setup for getting browser ready for executing tests.
	 * </p>
	 * <p>
	 * Ex:- page load timeout
	 * </p>
	 * 
	 */
	private static void browserEnvironmentSetup() {
		try {
			driver.get(Configuration.getUrl());
			CommonUtil.javaScriptToWaitForPage();
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(Configuration.getImplicit(), TimeUnit.SECONDS);
			getDriver().manage().timeouts().pageLoadTimeout(Configuration.getPageload(), TimeUnit.SECONDS);
			getDriver().manage().timeouts().setScriptTimeout(Configuration.getScript(), TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("Failed to set browser due to:- " + e.getMessage());
		}
	}

	/**
	 * <p>
	 * Setup the browser from configuration to execute test cases.
	 * </p>
	 * 
	 */
	private void browserSetup() {
		if (Configuration.getbrowser().equalsIgnoreCase("chrome")) {
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		}

		else if (Configuration.getbrowser().equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().mac().setup();
			driver = new FirefoxDriver();
		}

		else if (Configuration.getbrowser().equalsIgnoreCase("mobileView"))
			mobileViewSetup();
		else
			Assert.fail("Either browser name provided is not implemented or its a null");
	}

	/**
	 * <p>
	 * Experimental, help in viewing site in a mobile portrait size
	 * </p>
	 * 
	 */
	private void mobileViewSetup() {
		Map<String, Object> deviceMetrics = new HashMap<>();
		deviceMetrics.put("width", 360);
		deviceMetrics.put("height", 640);
		deviceMetrics.put("pixelRatio", 3.0);

		Map<String, Object> mobileEmulation = new HashMap<>();
		mobileEmulation.put("deviceMetrics", deviceMetrics);
		mobileEmulation.put("userAgent",
				"Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(chromeOptions);
	}

}
