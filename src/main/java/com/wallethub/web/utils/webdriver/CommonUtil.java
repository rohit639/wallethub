package com.wallethub.web.utils.webdriver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;

import com.wallethub.web.core.BaseWebdriver;
import com.wallethub.web.core.Configuration;
import com.wallethub.web.core.Constant;
import com.wallethub.web.core.LoggerClass;
import com.wallethub.web.core.reports.AllureReport;
import com.wallethub.web.utils.RandomData;

/**
 * Webdriver common utils
 * 
 * @author Rohit P Kumar
 *
 */
public class CommonUtil {

	private static final Logger logger = LoggerClass.createLogger();

	/**
	 * <p>
	 * Scroll the web page to the web element.
	 * </p>
	 * 
	 * @param aElement element till the web page needs to be scrolled.
	 */
	public static void scrolIntoElement(WebElement aElement) {
		((JavascriptExecutor) BaseWebdriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", aElement);
	}

	/**
	 * <p>
	 * get Default action of WebDriver actions class
	 * </p>
	 * 
	 * @return default Actions object to perform actions on WebElement
	 */
	public static Actions getActionMethod() {
		return new Actions(BaseWebdriver.getDriver());
	}

	/**
	 * <p>
	 * Wait for further actions till the whole web page loads completely
	 * </p>
	 */
	public static void javaScriptToWaitForPage() {
		String pageLoadStatus;
		do {
			JavascriptExecutor js = (JavascriptExecutor) BaseWebdriver.getDriver();
			pageLoadStatus = (String) js.executeScript("return document.readyState");
		} while (!pageLoadStatus.endsWith("complete"));
	}

	/**
	 * <p>
	 * Some time webdriver clicks doesn't work as page interaction intruppted due to
	 * Async loading, In that case Js click helps to click.
	 * </p>
	 */
	public static void javaScriptClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) BaseWebdriver.getDriver();
		executor.executeScript("arguments[0].click();", element);
	}

	/**
	 * <p>
	 * Switch to a WebFrame based on frame element
	 * </p>
	 * 
	 * @param webElement WebFrame element
	 * @return true if frame available else false
	 */
	public static boolean switchToFrame(WebElement webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), Configuration.getExplicit());
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement));
			return true;

		} catch (NoSuchFrameException e) {
			logger.warn("Failed to switch to frame as  ", e);
			return false;
		}
	}

	/**
	 * <p>
	 * Takes a screenShot of a Web page
	 * </p>
	 * 
	 * @param driver instance of WebDriver
	 * @return saved image location in a string format
	 */
	public static String createScreenshot(WebDriver driver, String testCaseName) {

		String screenShotImageName = testCaseName + "_" + RandomData.getToDayDate("E_MMM_hh:mm:s:sSSS:a");
		// UUID uuid = UUID.randomUUID();
		TakesScreenshot ts = ((TakesScreenshot) driver);
		AllureReport.takeScreenshot(ts);
		File scrFile = ts.getScreenshotAs(OutputType.FILE);
		try {
			// copy file object to designated location
			FileUtils.copyFile(scrFile,
					new File(Constant.REPORT_LOCATION + Constant.IMAGE_LOCATION + screenShotImageName + ".png"));
		} catch (IOException e) {
			logger.error("Error while generating screenshot:\n" + e.toString());
		}
		logger.debug("The Failed test case screenshot saved as : " + Constant.IMAGE_LOCATION + screenShotImageName
				+ ".png. Please find it in logs/images folder ");
		return Constant.IMAGE_LOCATION + screenShotImageName + ".png";
	}

	/**
	 * <p>
	 * Returns a boolean value if an input is selected or not
	 * <p/>
	 * 
	 * @param element
	 * @return
	 */
	public static boolean isElementSelected(WebElement element) {
		try {
			return element.isSelected();
		} catch (Exception e) {
			logger.error("Error while verifying selection of an element");
			return false;
		}
	}

	/**
	 * <p>
	 * There are cases where webDriver clicks fails. We can use mouse clicks in such
	 * cases.
	 * </p>
	 * 
	 * @param element
	 * @param driver
	 */
	public static void mouseClick(WebElement element, WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}

	/**
	 * Click on visible element if 2 or more elements are found
	 *
	 * @param elements
	 * @throws InterruptedException
	 */
	public static void clickOnVisibleElement(List<WebElement> elements) throws InterruptedException {
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), Configuration.getExplicit());
				wait.until(ExpectedConditions.visibilityOf(element));
				element.click();
				Thread.sleep(500);
				break;
			}
		}
	}

	/**
	 * Switch to the latest tab
	 */
	public static void switchToLatestTab() {
		ArrayList<String> tabs = new ArrayList<>(BaseWebdriver.getDriver().getWindowHandles());
		BaseWebdriver.getDriver().switchTo().window(tabs.get(tabs.size() - 1));
	}

}
