package com.wallethub.web.utils.webdriver;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.wallethub.web.core.LoggerClass;

/**
 * <p>
 * Implementation of selenium support select class
 * </p>
 * <p>
 * All cases related to select class.
 * </p>
 * 
 * @author Rohit P Kumar
 *
 */
public class SelectUtils {

	private static final Logger logger = LoggerClass.createLogger();

	/**
	 * <p>
	 * To get default select
	 * </p>
	 *
	 * @param element webdriver webelement
	 * @return default select element
	 */
	public static Select getSelect(WebElement element) {
		return new Select(element);
	}

	/**
	 * <p>
	 * To select option based on visible text
	 * </p>
	 *
	 * @param webElement  webdriver webelement
	 * @param visibleText the text which needs to be selected
	 * @return if selection is done true else false.
	 */
	public static boolean selectBasedOnVisibleText(WebElement webElement, String visibleText) {
		try {
			assertTrue(WaitUtil.isElemntVisble(webElement), "Failed to view select option");
			Select select = new Select(webElement);
			select.selectByVisibleText(visibleText);
			return true;

		} catch (ElementNotSelectableException e) {
			logger.error("Failed to select due to ", e);
			return false;
		}
	}

}
