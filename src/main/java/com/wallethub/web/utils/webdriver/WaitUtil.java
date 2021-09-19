package com.wallethub.web.utils.webdriver;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.wallethub.web.core.BaseWebdriver;
import com.wallethub.web.core.Configuration;
import com.wallethub.web.core.LoggerClass;
import com.wallethub.web.core.reports.ExtendedReport;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;

/**
 * <p>
 * All Static methods to get WebDriver wait methods
 * </p>
 * 
 * @author Rohit P Kumar
 */

public class WaitUtil {

	private static final Logger logger = LoggerClass.createLogger();

	/**
	 * <p>
	 * Dynamic pause either till element get visible or default time expires
	 * </p>
	 *
	 * @param webElement element which needs to be visible
	 * @return if element become visible returns true else false
	 * 
	 */
	public static boolean isElemntVisble(WebElement webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), Configuration.getExplicit());
			wait.until(ExpectedConditions.visibilityOf(webElement));
			return true;
		} catch (ElementNotVisibleException e) {
			reportException("Could not find visibility of element " + webElement + " " + e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * <p>
	 * Dynamic pause either till element get visible or default time expires
	 * </p>
	 *
	 * @param webElement element which needs to be visible
	 * @return if element become visible returns true else false
	 * 
	 */
	public static boolean isElemntVisbleRefress(WebElement webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), Configuration.getExplicit());
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(webElement)));
			return true;
		} catch (ElementNotVisibleException e) {
			reportException("Could not find visibility of element " + webElement + " " + e.getMessage());
			return false;
		}
	}

	/**
	 * <p>
	 * Dynamic pause either till element get visible or provided time expires
	 * </p>
	 *
	 * @param webElement  element which needs to be visible
	 * @param timeSeconds maximum seconds to wait
	 * @return if element become visible returns true else false
	 * 
	 */
	public static boolean isElemntVisble(WebElement webElement, int timeSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), timeSeconds);
			wait.until(ExpectedConditions.visibilityOf(webElement));
			return true;
		} catch (ElementNotVisibleException e) {
			reportException("Could not find visablity of element " + webElement + " " + e.getMessage());
			return false;
		}
	}

	/**
	 * <p>
	 * Dynamic pause either till all elements get invisible or default time expires
	 * </p>
	 *
	 * @param webElement list of all elements which needs to be invisible
	 * @return if all element become visible returns true else false
	 * 
	 */
	public static boolean isElemntInVisble(List<WebElement> webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), Configuration.getExplicit());
			wait.until(ExpectedConditions.invisibilityOfAllElements(webElement));
			return true;
		} catch (Exception e) {
			reportException("Could not wait for  Invisablity of element " + webElement + " " + e.getMessage());
			return false;
		}
	}

	/**
	 * <p>
	 * Dynamic pause either till All element get invisible or provided time expires
	 * </p>
	 *
	 * @param webElement  List of all elements which needs to be invisible
	 * @param timeSeconds maximum seconds to wait
	 * @return if all element become invisible returns true else false
	 * 
	 */
	public static boolean isElemntInVisble(List<WebElement> webElement, int timeSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), timeSeconds);
			wait.until(ExpectedConditions.invisibilityOfAllElements(webElement));
			return true;
		} catch (Exception e) {
			reportException("Could not wait for  Invisablity of element " + webElement + " " + e.getMessage());
			return false;
		}
	}

	/**
	 * <p>
	 * Dynamic pause either till element get invisible or default time expires
	 * </p>
	 *
	 * @param webElement element which needs to be invisible
	 * @return if element become Invisible returns true else false
	 * 
	 */
	public static boolean isElemntInVisble(WebElement webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), Configuration.getExplicit());
			wait.until(ExpectedConditions.invisibilityOf(webElement));
			return true;
		} catch (Exception e) {
			reportException("Could not wait for  Invisablity of element " + webElement + " " + e.getMessage());
			return false;
		}
	}

	/**
	 * <p>
	 * Dynamic pause either till element get invisible or provided time expires
	 * </p>
	 *
	 * @param webElement  elements which needs to be invisible
	 * @param timeSeconds maximum seconds to wait
	 * @return if element become Invisible returns true else false
	 * 
	 */
	public static boolean isElemntInVisble(WebElement webElement, int timeSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), timeSeconds);
			wait.until(ExpectedConditions.invisibilityOf(webElement));
			return true;
		} catch (Exception e) {
			reportException("Could not wait for  Invisablity of element " + webElement + " " + e.getMessage());
			return false;
		}
	}

	/**
	 * <p>
	 * Dynamic pause either till element become clickable or default time expires
	 * </p>
	 *
	 * @param webElement element which needs to be clickable
	 * @return if element become clickable returns true else false
	 * 
	 */
	public static boolean isElemntclickable(WebElement webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), Configuration.getExplicit());
			Assert.assertTrue(isElemntVisble(webElement));
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
			return true;
		} catch (ElementClickInterceptedException e) {
			reportException("Could not find element  " + webElement + " as clickable. " + e.getMessage());
			return false;
		}

		catch (Exception e) {
			reportException("Could not find element  " + webElement + " as clickable. " + e.getMessage());
			return false;
		}
	}

	/**
	 * <p>
	 * Dynamic pause either till element get clickable or provided time expires
	 * </p>
	 *
	 * @param webElement  elements which needs to be clickable
	 * @param timeSeconds maximum seconds to wait
	 * @return if element become clickable returns true else false
	 * 
	 */
	public static boolean isElemntclickable(WebElement webElement, int timeSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), timeSeconds);
			Assert.assertTrue(isElemntVisble(webElement));
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
			return true;
		} catch (ElementClickInterceptedException e) {
			reportException("Could not find element  " + webElement + " as clickable. " + e.getMessage());
			return false;
		}

		catch (Exception e) {
			reportException("Could not find element  " + webElement + " as clickable. " + e.getMessage());
			return false;
		}
	}

	/**
	 * <p>
	 * Dynamic pause either till element text equals provided text or default time
	 * expires
	 * </p>
	 *
	 * @param webElement     element which text needs to be equals
	 * @param textToValidate string which needs to be used to validate element text
	 * @return if element become visible returns true else false
	 * 
	 */
	public static boolean isTextPresent(WebElement webElement, String textToValidate) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), Configuration.getExplicit());
			Assert.assertTrue(isElemntVisble(webElement), "Failed as element is not visiable ");
			wait.until(ExpectedConditions.textToBePresentInElement(webElement, textToValidate));
			return true;
		} catch (Exception e) {
			reportException("Could not find Text " + textToValidate + " of element " + ". But found "
					+ webElement.getText() + " " + e.getMessage());
			return false;
		}
	}

	/**
	 * <p>
	 * Dynamic pause either till element text equals provided text or provided time
	 * expires
	 * </p>
	 *
	 * @param webElement     element which text needs to be equals
	 * @param textToValidate string which needs to be used to validate element text
	 * @param timeSeconds    maximum seconds to wait
	 * @return if element become clickable returns true else false
	 * 
	 */
	public static boolean isTextPresent(WebElement webElement, String textToValidate, int timeSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), timeSeconds);
			Assert.assertTrue(isElemntVisble(webElement), "Failed as element is not visiable ");
			wait.until(ExpectedConditions.textToBePresentInElement(webElement, textToValidate));
			return true;
		} catch (Exception e) {
			reportException("Could not find Text " + textToValidate + " of element " + ". But found "
					+ webElement.getText() + " " + e.getMessage());
			return false;
		}
	}

	/**
	 * <p>
	 * Dynamic pause either till element text changed to provided text or default
	 * time expires
	 * </p>
	 *
	 * @param webElement     element which text needs to be changed
	 * @param textToValidate string which needs to be used to validate element text
	 * @return if element become visible returns true else false
	 * 
	 */
	public static boolean isTextToBe(WebElement webElement, String textToValidate) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), Configuration.getExplicit());
			Assert.assertTrue(isElemntVisble(webElement), "Failed as element is not visiable ");
			wait.until(ExpectedConditions.textToBe((By) webElement, textToValidate));
			return true;
		} catch (Exception e) {
			reportException("Could not find Text " + textToValidate + " of element " + ". But found "
					+ webElement.getText() + " " + e.getMessage());
			return false;
		}
	}

	/**
	 * <p>
	 * Dynamic pause either till element text changed to provided text or default
	 * time expires
	 * </p>
	 *
	 * @param webElement     element which text needs to be changed
	 * @param textToValidate string which needs to be used to validate element text
	 * @param timeSeconds    maximum seconds to wait
	 * @return if element become clickable returns true else false
	 * 
	 */
	public static boolean isTextToBe(WebElement webElement, String textToValidate, int timeSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), timeSeconds);
			Assert.assertTrue(isElemntVisble(webElement), "Failed as element is not visiable ");
			wait.until(ExpectedConditions.textToBe((By) webElement, textToValidate));
			return true;
		} catch (Exception e) {
			reportException("Could not find Text " + textToValidate + " of element " + ". But found "
					+ webElement.getText() + " " + e.getMessage());
			return false;
		}
	}

	/**
	 * <p>
	 * Dynamic pause either till all elements get visible or default time expires
	 * </p>
	 *
	 * @param webElements list of all elements which needs to be visible
	 * @return if element become visible returns true else false
	 * 
	 */
	public static boolean isElemntVisble(List<WebElement> webElements) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), Configuration.getExplicit());
			wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
			return true;
		} catch (ElementNotVisibleException e) {
			reportException("Could not find visablity of all elements " + webElements + " " + e.getMessage());
			return false;
		}
	}

	/**
	 * <p>
	 * Dynamic pause either till all element get visible or provided time expires
	 * </p>
	 *
	 * @param webElements list of all elements which needs to be visible
	 * @param timeSeconds maximum seconds to wait
	 * @return if element become visible returns true else false
	 * 
	 */
	public static boolean isElemntVisble(List<WebElement> webElements, int timeSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), timeSeconds);
			wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
			return true;
		} catch (ElementNotVisibleException e) {
			reportException("Could not find visablity of all elements " + webElements + " " + e.getMessage());
			return false;
		}
	}

	/**
	 * <p>
	 * Dynamic pause either till page tile equals to text provided or default time
	 * expires
	 * </p>
	 *
	 * @param title required page tile
	 * @return if element become visible returns true else false
	 * 
	 */
	public static boolean isTitlePresent(String title) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), Configuration.getExplicit());
			CommonUtil.javaScriptToWaitForPage();
			wait.until(ExpectedConditions.titleIs(title));
			return true;
		} catch (Exception e) {
			reportException("Could not find title as provided : " + title + " " + e.getMessage());
			return false;
		}
	}

	/**
	 * <p>
	 * Dynamic pause either till page tile equals to text provided or provided time
	 * expires
	 * </p>
	 *
	 * @param title       required page tile
	 * @param timeSeconds maximum seconds to wait
	 * @return if element become visible returns true else false
	 * 
	 */
	public static boolean isTitlePresent(String title, int timeSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseWebdriver.getDriver(), timeSeconds);
			CommonUtil.javaScriptToWaitForPage();
			wait.until(ExpectedConditions.titleIs(title));
			return true;
		} catch (Exception e) {
			reportException("Could not find title as provided : " + title + " " + e.getMessage());
			return false;
		}
	}

	private static void reportException(String message) {
		logger.debug(message);
		Allure.step(message, Status.BROKEN);
		Reporter.log(message);
		ExtendedReport.test.log(com.aventstack.extentreports.Status.WARNING, message);

	}
}
