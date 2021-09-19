package com.wallethub.web.pages;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.wallethub.web.core.BaseWebdriver;
import com.wallethub.web.core.LoggerClass;
import com.wallethub.web.core.reports.ExtendedReport;
import com.wallethub.web.locators.Locators;
import com.wallethub.web.utils.webdriver.WaitUtil;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;

/**
 * All web pages must extend this class. This contains all basic helper methods
 * related to pages.
 *
 * @author Rohit P Kumar
 */
public abstract class BasePage {

	protected static final Logger logger = LoggerClass.createLogger();

	public BasePage(String title,String errorMsg) {
		PageFactory.initElements(BaseWebdriver.getDriver(), this);
		Assert.assertTrue(validateTitle(title,errorMsg), errorMsg);
	}
	
	
	private boolean validateTitle(String title,String errorMsg) {
		try {
			Assert.assertTrue(WaitUtil.isTitlePresent(title),
					errorMsg);
			return true;
		} catch (Exception e) {
			errorLogs(errorMsg);
			return false;
		}
	}

	/**
	 * This helps in printing the info logs in Console, Allure, Extended and, TestNG
	 * Reports.
	 *
	 * @param message the sentence which a user wants to log in as info.
	 */
	public void infoLogs(String message) {
		logger.info(message);
		Allure.step(message);
		Reporter.log(message);
		ExtendedReport.test.log(com.aventstack.extentreports.Status.INFO, message);
	}

	/**
	 * This helps in printing the error logs in Console, Allure, Extended and,
	 * TestNG Reports.
	 *
	 * @param message the sentence which a user wants to log in as error.
	 */
	public void errorLogs(String message) {
		logger.error(message);
		Allure.step(message, Status.FAILED);
		Reporter.log(message);
		ExtendedReport.test.log(com.aventstack.extentreports.Status.ERROR, message);
	}

}
